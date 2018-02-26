package com.dredh.codec;

import com.dredh.handler.HandlerMapping;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RouterMapper {

    private Map<String, HandlerMapping> map = new ConcurrentHashMap<>();

    public void registerHandler(String name, HandlerMapping handler) {
        map.put(name, handler);
    }

    public HandlerMapping getHandlerMapping(String name) {
        return map.get(name);
    }
}
