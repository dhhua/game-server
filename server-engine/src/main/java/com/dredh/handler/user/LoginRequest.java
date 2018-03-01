package com.dredh.handler.user;

public class LoginRequest {

    private String account;

    private String password;

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
