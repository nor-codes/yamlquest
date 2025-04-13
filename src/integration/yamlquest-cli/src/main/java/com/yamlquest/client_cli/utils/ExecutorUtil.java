package com.yamlquest.client_cli.utils;

import com.yamlquest.client_cli.exception.InvalidFileException;
import com.yamlquest.client_cli.exception.MissingOptionException;
import com.yamlquest.client_cli.exception.MissingParameterException;
import com.yamlquest.http_client.ClientResponse;
import com.yamlquest.http_client.RequestClient;
import com.yamlquest.parser.ParseOutput;
import com.yamlquest.parser.Parser;

import java.io.File;
import java.io.IOException;

public class ExecutorUtil {

    private ExecutorUtil(){}

    public static void process(boolean fileOption, File file){
        if (!fileOption){
            throw new MissingOptionException("The file option was specified, but no valid file was provided. Please specify a valid file path or ensure the file exists.");
        }

        if (file == null){
            throw new MissingParameterException("The file to execute request on was not specified.");
        }

        if (!FileUtil.isYaml(file)){
            throw new InvalidFileException("The provided file is not a valid YAML file. Please specify a file with a '.yaml' or '.yml' extension.");
        }

        try{
            Parser parser = new Parser();
            ParseOutput parseOutput = parser.parse(file.getPath());
            RequestClient client = new RequestClient();

            RequestMapper.mapParseOutputToRequestInfo(parseOutput).forEach(requestInfo -> {
                ClientResponse clientResponse = null;
                try {
                    clientResponse = client.call(requestInfo);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Formatter<ClientResponse> formatter = new RequestWithoutValidationsFormatter<>();
                FormatOutput formatOutput = formatter.format(clientResponse);
                System.out.println(formatOutput.toString());
            });
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
