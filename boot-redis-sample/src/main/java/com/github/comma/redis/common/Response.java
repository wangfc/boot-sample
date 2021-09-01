package com.github.comma.redis.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 11:13
 */
@Getter
public final class Response<T> implements Serializable {

    private static final long serialVersionUID = -3021258782151667905L;

    private String code;

    private String message;

    private T data;

    private Response() {

    }


    public static Response success() {
        return new Response().code("000000").message("success");
    }

    public static Response failure() {
        return new Response().code("999999").message("failure");
    }


    public Response code(String code) {
        this.code = code;
        return this;
    }

    public Response message(String message) {
        this.message = message;
        return this;
    }

    public Response data(T data) {
        this.data = data;
        return this;
    }

}