package com.dredh;

import com.dredh.handler.user.IUser;

public class User implements IUser {

    private Long userId;
    private String name;

    public User(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public long getUserId() {
        return 0;
    }
}
