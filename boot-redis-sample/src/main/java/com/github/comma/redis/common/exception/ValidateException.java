package com.github.comma.redis.common.exception;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
public class ValidateException extends BusinessException {


    private static final long serialVersionUID = -2403244377842307308L;

    public ValidateException() {
        super("100001");
    }

    public ValidateException(String message) {
        super("100001", message);
    }

    public ValidateException(String code, String message) {
        super(code, message);
    }
}
