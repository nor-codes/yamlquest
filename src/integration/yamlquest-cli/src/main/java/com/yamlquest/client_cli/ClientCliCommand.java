package com.yamlquest.client_cli;

import com.yamlquest.client_cli.command.ExecuteRequestCommand;
import com.yamlquest.client_cli.utils.VersionProvider;
import io.micronaut.configuration.picocli.PicocliRunner;

import picocli.CommandLine.Command;

@Command(name = "yq-cli", description = "...",
        mixinStandardHelpOptions = true,
        subcommands = {ExecuteRequestCommand.class},
        versionProvider = VersionProvider.class)
public class ClientCliCommand implements Runnable {

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(ClientCliCommand.class, args);
    }

    public void run() {
    }
}
