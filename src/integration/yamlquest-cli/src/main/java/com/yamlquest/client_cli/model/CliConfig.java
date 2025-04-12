package com.yamlquest.client_cli.model;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("config")
public class CliConfig {
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}