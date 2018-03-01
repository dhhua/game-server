package com.dredh.handler;

import com.dredh.common.annotation.Command;
import com.dredh.common.annotation.CommandHandler;

@CommandHandler(name = "test")
public class TestHandler {

    @Command
    public String test(String name) {
        System.out.println("name = " + name);
        return "SUCCESS";
    }


}
