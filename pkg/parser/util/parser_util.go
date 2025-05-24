package parser_util

import (
	"fmt"
	"io"
	"net/http"
	"os"
	"strings"
)

func ReadContent(filePath string)([]byte, error){
	
	if strings.HasPrefix(filePath,"http") || strings.HasPrefix(filePath,"https") {
		return readRemoteFile(filePath)
	}else{
		return readLocalFile(filePath)
	}
}

func readRemoteFile(url string)([]byte, error){

	resp, err := http.Get(url)

	if err != nil {
		return nil, fmt.Errorf("failed to fetch remote file: %w", err)
	}

	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return nil, fmt.Errorf("failed to fetch remote file, status code: %d", resp.StatusCode)
	}

	body, err := io.ReadAll(resp.Body)

	if err != nil {
		return nil, fmt.Errorf("failed to read remote file content: %w", err)
	}

	return body, nil
}

func readLocalFile(filePath string)([]byte,error){
	content,err := os.ReadFile(filePath)

	if err != nil {
		return nil,fmt.Errorf("failed to read file: %w", err)
	}

	return content, nil
}