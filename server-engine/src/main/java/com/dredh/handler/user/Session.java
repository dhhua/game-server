package com.dredh.handler.user;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private IUser user;
    private Channel channel;
    private String ip;
    private Map<Object, Object> extras;

    public Session(IUser user, Channel channel, String ip) {
        this.user = user;
        this.channel = channel;
        this.ip = ip;
        this.extras = new HashMap<>();
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map<Object, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<Object, Object> extras) {
        this.extras = extras;
    }

    public void putExtra(Object key, Object value) {
        extras.put(key, value);
    }

    public <T> T getExtra(Object key) {
        return (T) extras.get(key);
    }

}
