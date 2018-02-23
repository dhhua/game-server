package com.dredh.codec.protostuff;

import com.dredh.codec.Message;
import com.dredh.handler.Handler;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProtostuffHandlerAdapter implements Handler<Message, Object> {

    private Method commandMethod;
    private Class parameterClazz;
    private Object handler;

    public ProtostuffHandlerAdapter(Method commandMethod, Class parameterClazz, Object handler) {
        this.commandMethod = commandMethod;
        this.parameterClazz = parameterClazz;
        this.handler = handler;
    }

    @Override
    public Object handleRequest(Message request) {
        Object[] parameters = null;
        if (parameterClazz != null) {
            Schema schema = RuntimeSchema.getSchema(parameterClazz);
            Object parameter = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(request.getBody(), parameter, schema);
            parameters = new Object[]{parameter};
        }
        try {
            return commandMethod.invoke(handler, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
