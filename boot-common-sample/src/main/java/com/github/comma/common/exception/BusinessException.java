package com.github.comma.common.exception;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
public class BusinessException extends RuntimeException {

    private int code ;

    public BusinessException(){
        super();
    }

    public BusinessException(int code){
        super();
        this.code = code ;
    }

    public BusinessException(int code , String message){
        super(message);
        this.code = code ;
    }

    public BusinessException(int code , String message, Throwable cause) {
        super(message, cause);
        this.code = code ;
    }

    public int getCode() {
        return code;
    }
}
