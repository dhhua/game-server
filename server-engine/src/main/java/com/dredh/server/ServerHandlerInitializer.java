package com.dredh.server;

import com.dredh.codec.CodecInitializer;
import com.dredh.handler.HeartBeatHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by honghua.dong on 2017/8/30.
 */
@Component
public class ServerHandlerInitializer extends ChannelInitializer<Channel> {

    @Autowired
    private CodecInitializer codecInitializer;

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline().addLast(codecInitializer);
        channel.pipeline().addLast(new IdleStateHandler(0, 0, 10, TimeUnit.SECONDS));
        channel.pipeline().addLast(new HeartBeatHandler());
    }
}
