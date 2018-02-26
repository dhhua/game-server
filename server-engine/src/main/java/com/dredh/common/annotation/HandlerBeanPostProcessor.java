package com.dredh.common.annotation;

import com.dredh.codec.RouterMapper;
import com.dredh.handler.HandlerMapping;
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
    private RouterMapper routerMapper;
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
                    Class parameterClazz = null;
                    if (method.getParameterTypes().length > 0) {
                        parameterClazz = method.getParameterTypes()[0];
                    }
                    HandlerMapping handlerMapping = new HandlerMapping(method, parameterClazz, bean);
                    routerMapper.registerHandler(handlerName.toString(), handlerMapping);
                }
            }
        }
        return bean;
    }
}
