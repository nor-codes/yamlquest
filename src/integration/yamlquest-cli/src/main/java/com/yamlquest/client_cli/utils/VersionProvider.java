package com.yamlquest.client_cli.utils;

import com.yamlquest.client_cli.model.CliConfig;
import io.micronaut.context.ApplicationContext;
import picocli.CommandLine;;

public class VersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() throws Exception {
        try(ApplicationContext ctx = ApplicationContext.run()){
            CliConfig config = ctx.getBean(CliConfig.class);
            return new String[] { CliBannerUtil.getBanner(config.getVersion()) };
        }
    }
}
