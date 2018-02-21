package com.dredh.codec.protostuff;

import com.dredh.model.CommandRequest;
import com.dredh.model.CommandResponse;
import com.google.common.base.MoreObjects;

public class ProtostuffMessage implements CommandRequest, CommandResponse {

    private byte type;

    private String route;
    private byte[] body;

    public ProtostuffMessage (byte type, String route, byte[] body) {
        this.type = type;
        this.route = route;
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public String getRoute() {
        return route;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("route", route)
                .add("body", body)
                .toString();
    }
}
