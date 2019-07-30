package com.github.comma.common.exception;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
public class ValidateException extends BusinessException {
    private static final long serialVersionUID = 1L;

    public ValidateException(String message) {
        super(1001,message);
    }

    public ValidateException(int code, String message) {
        super(code, message);
    }
}
