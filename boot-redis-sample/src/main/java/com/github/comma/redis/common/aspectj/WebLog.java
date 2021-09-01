package com.github.comma.redis.common.aspectj;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author wangfc
 * @date 2018-08-05 17:44
 */
@Configuration
@Aspect
@Order(-2)
@Slf4j
public class WebLog {

    private static ThreadLocal<Long> TIME_CONTEXT = new ThreadLocal<>();
    private Pattern pattern = Pattern.compile("\\w*page|list\\w*^$");

    @Pointcut("execution( public * com.github.comma.redis.web..*Controller.*(..))")
    public void log() {
    }


    @Before("log()")
    public void before(JoinPoint joinPoint) {
        TIME_CONTEXT.set(System.currentTimeMillis());
        log.debug("WebLogAspect before()...");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("getDeclaringType:{}", joinPoint.getSignature().getDeclaringType());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning(value = "log()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) {
        // 处理完请求，返回内容
        log.info("WebLogAspect.afterReturning()...");
        if (printRetValue(joinPoint.getSignature().getName())) {
            log.info("return value:{}", JSON.toJSONString(ret));
        }
        log.info("it's cost {} ms ", System.currentTimeMillis() - TIME_CONTEXT.get());
        TIME_CONTEXT.remove();
    }

    private boolean printRetValue(String methodName) {
        return !pattern.matcher(methodName.toLowerCase()).find();
    }
}