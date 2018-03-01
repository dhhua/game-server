package com.dredh.model;

import com.google.common.base.MoreObjects;

public class ServerResult {

    private int result;

    private String message;

    public ServerResult(int result, String message) {
        this.result = result;
        this.message = message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("result", result)
                .add("message", message)
                .toString();
    }
}
