package com.dredh.handler;

public interface Handler<R,T> {

    T handleRequest(R request);

}
