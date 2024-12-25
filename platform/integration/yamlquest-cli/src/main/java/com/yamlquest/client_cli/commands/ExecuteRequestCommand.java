package com.yamlquest.client_cli.commands;

import com.yamlquest.client_cli.utils.*;
import com.yamlquest.http_client.ClientResponse;
import com.yamlquest.http_client.RequestClient;
import com.yamlquest.http_client.RequestInfo;
import com.yamlquest.parser.ParseOutput;
import com.yamlquest.parser.Parser;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Command(name = "execute")
public class ExecuteRequestCommand extends BaseCommand {

    @Option(names = {"-f", "--file"}, description = "Read request data from file")
    private String fileOption;

    @Parameters(index = "0", description = "The File that contains requests")
    private File file;

    @Override
    public void help() {

    }

    //yq-cli execute --file [File Path]
    //yq-cli execute --file . => execute request at the current directory.
    @Override
    public void process() {
        if (!fileOption.isEmpty() && !fileOption.isBlank() && file==null){
            throw new RuntimeException("The file option was specified, but no valid file was provided. Please specify a valid file path or ensure the file exists.");
        }

        if (!FileUtil.isYaml(file)){
            throw new RuntimeException("The provided file is not a valid YAML file. Please specify a file with a '.yaml' or '.yml' extension.");
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
