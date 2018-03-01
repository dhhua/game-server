package com.dredh.handler.user;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnBean(UserHandlerComponent.class)
public class UserManager {

    private Map<Long, Session> sessionMap = new ConcurrentHashMap<>();

    public void putSession(Long userId, Session session) {
        sessionMap.put(userId, session);
    }

    public void removeSession(Long userId) {
        sessionMap.remove(userId);
    }
}
