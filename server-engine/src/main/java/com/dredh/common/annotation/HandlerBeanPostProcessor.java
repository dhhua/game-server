package com.dredh.common.annotation;

import com.dredh.codec.CodecFacade;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Component
public class HandlerBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private CodecFacade codecFacade;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        CommandHandler commandHandler = AnnotationUtils.findAnnotation(bean.getClass(), CommandHandler.class);
        if (commandHandler != null) {
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
            for (Method method : methods) {
                if (AnnotationUtils.findAnnotation(method, Command.class) != null) {
                    StringBuilder handlerName = new StringBuilder(commandHandler.name());
                    handlerName.append("." + bean.getClass().getSimpleName());
                    handlerName.append("." + method.getName());
                    codecFacade.registerHandler(handlerName.toString(),bean, method);
                }
            }
            System.out.println(bean.getClass().getSimpleName());
        }
        return bean;
    }
}
