package com.github.comma.redis.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author wangfc
 * @description
 * @date 2019-09-13 21:19
 */
@Component
public final class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ac;

    public static ApplicationContext getApplicationContext() {
        Assert.notNull(ac, "applicationContext初始化失败");
        return ac;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) getApplicationContext().getBean(clazz);

    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
}