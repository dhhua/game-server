package com.dredh;

import com.dredh.common.annotation.Command;
import com.dredh.common.annotation.CommandHandler;
import com.dredh.model.ResponseType;

@CommandHandler(name = "response.user", sub = "UserHandler")
public class UserResponseHandler {

    @Command
    public void login(ResponseType result) {
        System.out.println(result);
    }
}
