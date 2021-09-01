package com.github.comma.redis.common.exception;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
public class NoRecordException extends BusinessException {

    private static final long serialVersionUID = -5218341981666791263L;

    public NoRecordException() {
        super("999998");
    }

    public NoRecordException(String message) {
        super("999998", message);
    }

    public NoRecordException(String code, String message) {
        super(code, message);
    }
}
