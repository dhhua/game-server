package com.dredh.model;

public class CmdHeader {

    private final byte type;
    private final String route;
    private boolean hasBody;

    public CmdHeader(byte type) {
        this.type = type;
        this.route = "";
        this.hasBody = false;
    }

    public CmdHeader(byte type, String route, boolean hasBody) {
        this.type = type;
        this.route = route;
        this.hasBody = hasBody;
    }

    public byte getType() {
        return type;
    }

    public String getRoute() {
        return route;
    }

    public boolean isHasBody() {
        return hasBody;
    }
}
