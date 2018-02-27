package com.dredh;

import com.dredh.common.annotation.Command;
import com.dredh.common.annotation.CommandHandler;

@CommandHandler(name = "response.test", sub = "TestHandler")
public class GameClientResponseHandler {

    @Command
    public void test(String response) {
        System.out.println(response);
    }
}
