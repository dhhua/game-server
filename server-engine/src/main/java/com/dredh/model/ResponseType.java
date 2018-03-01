package com.dredh.model;

public class ResponseType implements IResponse {
    private int code;

    public ResponseType(int code) {
        this.code = code;
    }

    public static final ResponseType SUCCESS = new ResponseType(1);
    public static final ResponseType FAIL = new ResponseType(0);
    @Override
    public int getResultCode() {
        return code;
    }


}
