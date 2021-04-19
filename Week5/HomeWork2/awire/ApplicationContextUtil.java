package com.example.awire;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description: ApplicationContext工具类
 * @author: xuxinrong
 * @version: [2021-04-19]
 **/
@Component
public class ApplicationContextUtil implements ApplicationContextAware
{
    private static ApplicationContext context;

    public static ApplicationContext getContext()
    {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        context = applicationContext;
    }
}
