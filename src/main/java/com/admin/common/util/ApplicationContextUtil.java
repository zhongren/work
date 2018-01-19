package com.admin.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by ZR_a on 2018/1/19.
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public static <T> T  getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext=applicationContext;
    }
}
