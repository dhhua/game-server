package com.dredh.handler;

import java.lang.reflect.Method;

public class HandlerMapping {

    private final Method commandMethod;
    private final Class parameterClazz;
    private final Object handler;

    public Method getCommandMethod() {
        return commandMethod;
    }

    public Class getParameterClazz() {
        return parameterClazz;
    }

    public Object getHandler() {
        return handler;
    }

    public HandlerMapping(Method commandMethod, Class parameterClazz, Object handler) {
        this.commandMethod = commandMethod;
        this.parameterClazz = parameterClazz;
        this.handler = handler;
    }
}
