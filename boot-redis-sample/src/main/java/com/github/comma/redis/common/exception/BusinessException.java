package com.github.comma.redis.common.exception;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException() {
        super();
    }

    public BusinessException(String code) {
        super();
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
