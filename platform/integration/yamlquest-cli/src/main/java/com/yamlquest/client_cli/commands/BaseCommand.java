package com.yamlquest.client_cli.commands;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

public abstract class BaseCommand implements Runnable{

    @Option(names = "--help", description = "Print command specific help")
    private String help;

    public abstract void help();

    public abstract void process() throws Exception;

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            throw new RuntimeException(e);
            // Print a nicely formatted red error message
//            System.err.println(CommandLine.Help.Ansi.AUTO.string("@|red,BOLD ERROR: " + e.getMessage() + "|@"));
//            CommandLine.usage(this, System.err); // Show command usage
        }
    }
}
