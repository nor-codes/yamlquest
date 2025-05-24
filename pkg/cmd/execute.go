package cmd

import (
	"fmt"
	"io"
	"log"
	"net/http"
	"strings"

	"github.com/nor-codes/yamqlquest-cli/pkg/parser"
	"github.com/spf13/cobra"
)

var filePath string

type executeOptions struct{
	path string
	source string
}

type RequestOutput struct {
	StatusCode string
	Body string
	Headers     map[string][]string `yaml:"headers,omitempty"`
}



func NewExecuteCmd() *cobra.Command {

	options :=&executeOptions{}
	var cmd = &cobra.Command{
		Use: "execute",
		Short: "Execute requests from a yaml file",
		Run: func(cmd *cobra.Command, args []string) {
			options.path = filePath
			options.source = ""
			run(options)
		},
	} 

	cmd.Flags().StringVar(&filePath, "file", "", "Path to the YAML file to execute")
	return cmd
}


func run(executeOptions *executeOptions){
	parseOutput, err := parser.Parse(executeOptions.path)

	if err != nil {
		log.Fatal("error occurred while attempting to parse requests: "+ err.Error())
	}

	for _, request := range parseOutput.Requests {
		requestOutput, err := makeCall(&request)

		if err != nil {
			log.Fatal("orror occurred on the request %w: %w",request.Name,err.Error())
		}
		printResponse(requestOutput,&request)
	}
}

func makeCall(request *parser.Request)(*RequestOutput,error){
	client := &http.Client{}

	var bodyReader io.Reader
	if request.Method != "GET" && request.Body != "" {
		bodyReader = strings.NewReader(request.Body)
	}

	req, err := http.NewRequest(request.Method,request.URL,bodyReader)

	if err != nil {
		return nil, fmt.Errorf("error occurred while creating request client")
	}

	for k, v := range request.Headers{
		req.Header.Add(k,v)
	}

	resp, err := client.Do(req)

	if err != nil{
		return nil,err
	}

	body , err := io.ReadAll(resp.Body)
	requestOutput :=  &RequestOutput{}
	if err != nil {
		return nil, fmt.Errorf("error occurred while creating request client")
	}

	defer resp.Body.Close()

	requestOutput.Headers = make(map[string][]string)

	for k, v := range resp.Header{
		requestOutput.Headers[k] = v
	}
	
	requestOutput.Body = string(body)
	requestOutput.StatusCode = resp.Status

	return requestOutput, nil
}


func printResponse(resp *RequestOutput, req *parser.Request) {
	fmt.Printf("======================================= HTTP Response: %s =======================================\n", req.Name)
	fmt.Printf("\nStatus Code : %s \n", resp.StatusCode)
	fmt.Println("------------------------------------------------------------------------------------------------")
	fmt.Println("Headers:")
	for k, v := range resp.Headers {
		fmt.Printf("  %-16s : %s\n", k, strings.Join(v, ", "))
	}
	fmt.Println("------------------------------------------------------------------------------------------------")
	fmt.Println("Body:")
	fmt.Println(resp.Body)
	fmt.Printf("\n\n")
}
