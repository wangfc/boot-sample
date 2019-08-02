package com.github.comma.excel;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/8/1
 */
public class StarterService {

    private String config;

    public StarterService(String config) {
        this.config = config;
    }

    public String[] split(String separatorChar) {
        return config.split(separatorChar);
    }
}
