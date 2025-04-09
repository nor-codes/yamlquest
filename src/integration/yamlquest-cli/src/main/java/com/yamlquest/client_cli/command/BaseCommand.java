package com.yamlquest.client_cli.command;

import com.yamlquest.client_cli.exception.MissingOptionException;
import picocli.CommandLine.Option;

public abstract class BaseCommand implements Runnable{

    @Option(names = "--help", description = "Print command specific help")
    private String help;

    public abstract void help();

    public abstract void process() throws Exception;

//    @Override
//    public void run() {
//        try {
//            process();
//        } catch (Exception e) {
//            throw e;
////            throw new RuntimeException(e);
//            // Print a nicely formatted red error message
////            System.err.println(CommandLine.Help.Ansi.AUTO.string("@|red,BOLD ERROR: " + e.getMessage() + "|@"));
////            CommandLine.usage(this, System.err); // Show command usage
//        }
//    }
@Override
public void run() {
    try {
        process();
    } catch (MissingOptionException e) {
        // Rethrow the specific exception so that the test can catch it.
        throw e;
    } catch (Exception e) {
        // Catch other exceptions as a generic RuntimeException.
        throw new RuntimeException(e);
    }
}
}
