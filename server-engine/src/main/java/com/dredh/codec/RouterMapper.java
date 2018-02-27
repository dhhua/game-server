package com.dredh.codec;

import com.dredh.handler.HandlerMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RouterMapper {

    private static final RouterMapper instance = new RouterMapper();

    private RouterMapper() {
    }

    private Map<String, HandlerMapping> map = new ConcurrentHashMap<>();

    public void registerHandler(String name, HandlerMapping handler) {
        map.put(name, handler);
    }

    public HandlerMapping getHandlerMapping(String name) {
        return map.get(name);
    }

    public static RouterMapper getInstance() {
        return instance;
    }
}
