package com.yamlquest.client_cli.command;
import com.yamlquest.client_cli.utils.*;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
@Command(name = "execute")
public class ExecuteRequestCommand extends BaseCommand {

    @Option(names = {"-f", "--file"}, description = "Read request data from file")
    private boolean fileOption;

    @Parameters(index = "0",paramLabel = "FILE", description = "The File that contains requests")
    private File file;

    @Override
    public void process() {
        ExecutorUtil.process(fileOption,file);
    }
}
