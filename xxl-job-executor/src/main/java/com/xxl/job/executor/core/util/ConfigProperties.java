package com.xxl.job.executor.core.util;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @author chenqingsong
 * @Description:
 * @Copyright (c) 浙江阿拉丁电子商务股份有限公司
 * @date 2018/11/23
 */
public class ConfigProperties {

    private static Properties config     = new Properties();

    private String            configPath = null;

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void init() {
        try {
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    public static Properties getConfig() {
        return config;
    }

    /**
     * Gets the.
     *
     * @param key the key
     * @return the string
     */
    public static String get(String key) {
        return config.getProperty(key);
    }

    /**
     * Gets the.
     *
     * @param key the key
     * @param defaultValue the default value
     * @return the string
     */
    public static String get(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}
