package com.github.comma.excel;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/8/1
 */
@ConfigurationProperties("comma.service")
public class StarterServiceProperties {
    private String config;

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }
}
