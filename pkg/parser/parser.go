package parser

import (
	"github.com/nor-codes/yamqlquest-cli/pkg/parser/util"
	yaml "sigs.k8s.io/yaml/goyaml.v3"
)

type RequestContent struct {
	Requests []Request `yaml:"requests"`
}


type Request struct {
	Name        string            `yaml:"name"`
	Method      string            `yaml:"method"`
	URL         string            `yaml:"url"`
	Headers     map[string]string `yaml:"headers,omitempty"`
	ContentType string            `yaml:"contentType"`
	Body        string            `yaml:"body,omitempty"`
}

type ParserOutput struct {
	RequestContent RequestContent
	ErrorContent string
}

func Parse(filePath string)(ParserOutput){

	var requestContent RequestContent
	content, err := util.ReadContent(filePath)

	if err != nil{
		return ParserOutput{ ErrorContent: err.Error()}
	}

	err = yaml.Unmarshal(content,&requestContent)
	if err != nil{
		return ParserOutput{ ErrorContent: err.Error()}
	}

	return ParserOutput{RequestContent: requestContent}
}