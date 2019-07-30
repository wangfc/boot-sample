package com.github.comma.common;

import com.github.comma.common.exception.BusinessException;
import com.github.comma.common.exception.ValidateException;
import com.github.comma.common.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wangfuchu
 * @desciption
 * @date 2019/7/29
 */
@SpringBootApplication
public class CommonApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
//                .bannerMode(Banner.Mode.OFF)
                .sources(CommonApplication.class)
                .run(args);
    }



    @Bean
    public ExceptionHandleAdvice exceptionHandleAdvice(){
        return new ExceptionHandleAdvice();
    }


    @ControllerAdvice
    @Slf4j
    static class ExceptionHandleAdvice {
        public ExceptionHandleAdvice() {
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        @ExceptionHandler({HttpMessageNotReadableException.class})
        public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
            log.error("参数解析失败", e);
            return Response.failure().message("could_not_read_json");
        }

        @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
        @ResponseBody
        @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
        public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
            log.error("不支持当前请求方法", e);
            return Response.failure().message("request_method_not_supported");
        }

        @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        @ResponseBody
        @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
        public Response handleHttpMediaTypeNotSupportedException(Exception e) {
            log.error("不支持当前媒体类型", e);
            return Response.failure().message("content_type_not_supported");
        }


        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        @ExceptionHandler({BusinessException.class})
        public Response handleBusinessException(BusinessException e) {
            log.error("业务异常", e);
            return Response.success().code(e.getCode()).message(e.getMessage());
        }

        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        @ExceptionHandler({ValidateException.class})
        public Response handleValidateException(ValidateException e) {
            log.error("校验异常", e);
            return Response.success().code(e.getCode()).message(e.getMessage());
        }


        @ResponseStatus(HttpStatus.OK)
        @ResponseBody
        @ExceptionHandler({Exception.class})
        public Response handleException(Exception e) {
            log.error("服务运行异常", e);
            return Response.failure().message("系统异常，请联系管理员");
        }
    }

}
