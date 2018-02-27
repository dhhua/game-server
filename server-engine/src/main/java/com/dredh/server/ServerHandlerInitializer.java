package com.dredh.server;

import com.dredh.codec.CodecInitializer;
import com.dredh.handler.CmdHandler;
import com.dredh.handler.HeartBeatHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnBean(ServerLaunder.class)
public class ServerHandlerInitializer extends ChannelInitializer<Channel> {

    @Autowired
    private CodecInitializer codecInitializer;

    @Autowired
    private CmdHandler cmdHandler;
    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline().addLast(codecInitializer);
        channel.pipeline().addLast(cmdHandler);
        channel.pipeline().addLast(new IdleStateHandler(0, 0, 10, TimeUnit.SECONDS));
        channel.pipeline().addLast(new HeartBeatHandler());
    }
}
