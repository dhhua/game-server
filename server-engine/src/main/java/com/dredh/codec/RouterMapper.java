package com.dredh.codec;

import com.dredh.handler.Handler;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RouterMapper {

    private Map<String, Handler> map = new ConcurrentHashMap<>();

    public void registerHandler(String name, Handler handler) {
        map.put(name, handler);
    }

    public Handler getHandler(String name) {
        return map.get(name);
    }
}
