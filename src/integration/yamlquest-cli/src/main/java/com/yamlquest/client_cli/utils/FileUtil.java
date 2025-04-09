package com.yamlquest.client_cli.utils;

import java.io.File;

public class FileUtil {
    private static final String YAML_EXTENSION_ONE = ".yaml";
    private static final String YAML_EXTENSION_TWO = ".yml";

    public static boolean isYaml(File file){
        if (file == null || !file.exists()) {
            return false;
        }
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(YAML_EXTENSION_ONE) || fileName.endsWith(YAML_EXTENSION_TWO);
    }
}
