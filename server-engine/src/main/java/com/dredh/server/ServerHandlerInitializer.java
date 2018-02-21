package com.dredh.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import org.springframework.stereotype.Component;

/**
 * Created by honghua.dong on 2017/8/30.
 */
@Component
public class ServerHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline().addLast(new ServerHandler());
    }
}
