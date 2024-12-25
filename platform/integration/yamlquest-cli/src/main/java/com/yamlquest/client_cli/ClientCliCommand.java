package com.yamlquest.client_cli;

import com.yamlquest.client_cli.commands.BaseCommand;
import com.yamlquest.client_cli.commands.ExecuteRequestCommand;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "yq-cli", description = "...",
        mixinStandardHelpOptions = true,subcommands = {ExecuteRequestCommand.class})
public class ClientCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(ClientCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
