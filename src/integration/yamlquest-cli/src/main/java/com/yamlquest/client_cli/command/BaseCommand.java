package com.yamlquest.client_cli.command;

import com.yamlquest.client_cli.exception.MissingOptionException;
import picocli.CommandLine.Option;

public abstract class BaseCommand implements Runnable{

    public abstract void process() throws Exception;

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
