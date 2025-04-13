package com.yamlquest.client_cli.utils;

public class CliBannerUtil {

    private static final String BANNER = """
__     _____  ____
\\ \\   / / _ \\|  _ \\
 \\ \\_/ / | | | |_) |
  \\   /| |_| |  __/
   |_|  \\___/|_|
""";

    private  CliBannerUtil(){}

    public static String getBanner(String version) {
        return BANNER + "\n  YAMLQUEST version: " + version;
    }
}
