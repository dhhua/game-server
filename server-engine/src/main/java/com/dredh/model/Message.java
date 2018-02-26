package com.dredh.model;

public class Message {

    private final CmdHeader header;
    private final Object body;

    public Message(CmdHeader header) {
        this.header = header;
        this.body = null;
    }

    public Message(CmdHeader header, Object body) {
        this.header = header;
        this.body = body;
    }

    public CmdHeader getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }
}
