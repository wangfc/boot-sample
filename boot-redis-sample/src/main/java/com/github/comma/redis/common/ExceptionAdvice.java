package com.github.comma.redis.common;

import com.github.comma.redis.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 13:48
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public Response handlerException(Exception e) {
        log.error("error: ", e);
        return Response.failure().message("系统异常");
    }

    @ExceptionHandler(value = BusinessException.class)
    public Response handlerBusinessException(BusinessException e) {
        log.error("error: ", e);
        return Response.failure().code(e.getCode()).message(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError allError : allErrors) {
            log.debug("param error={} ", allError.getDefaultMessage());
        }
        log.error("error: ", e);
        return Response.failure().code("100001").message("参数错误");
    }


}