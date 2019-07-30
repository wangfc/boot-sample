package com.github.comma.common.vo;

import java.io.Serializable;

/**
 * @author wangfuchu
 * @desciption response父类
 * @date 2019/7/29
 */
public final class Response<T> implements Serializable {

    private static int FAILURE_CODE = -1 ;
    private static final String FAILURE_MESSAGE = "failure" ;

    private static int SUCCESS_CODE =  0 ;
    private static final String SUCCESS_MESSAGE = "success" ;


    private int code ;

    private String message ;

    private T data;

    private Response(int code){
        this(code, null , null ) ;
    }

    private Response(int code ,String message){
        this(code, message,null ) ;
    }

    private Response(int code ,String message,T data){
        this.code = code ;
        this.message = message ;
        this.data = data ;
    }

    public static Response success(){
        return new Response(SUCCESS_CODE,SUCCESS_MESSAGE) ;
    }

    public static Response failure(){
       return new Response(FAILURE_CODE,FAILURE_MESSAGE) ;
    }

    public Response<T> code(int code){
        return new Response<>(code) ;
    }

    public Response<T> message(String message){
        this.message = message ;
        return this ;
    }

    public Response<T> data(T data){
        this.data = data;
        return this ;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
