package com.dredh.codec.protostuff;

import com.dredh.codec.CodecFacade;
import com.dredh.codec.RouterMapper;
import com.dredh.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class ProtostuffCodecFacade implements CodecFacade {

    @Autowired
    private RouterMapper routerMapper;
    @Override
    public void registerHandler(String handlerName,Object handlerObject, Method handleMethod) {
        Handler handler = new ProtostuffHandlerAdapter(handleMethod, handleMethod.getParameterTypes()[0], handlerObject);
        routerMapper.registerHandler(handlerName, handler);
    }


}
