package com.dredh.codec;


import java.lang.reflect.Method;

public interface CodecFacade {

    void registerHandler(String handlerName, Object handlerObject, Method handleMethod);
}
