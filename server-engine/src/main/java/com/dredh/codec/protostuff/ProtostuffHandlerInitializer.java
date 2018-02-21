package com.dredh.codec.protostuff;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProtostuffHandlerInitializer extends ChannelInitializer<Channel> {

    @Autowired
    private ProtostuffMessageDecoder protostuffMessageDecoder;
    @Autowired
    private ProtostuffMessageEncoder protostuffMessageEncoder;
    @Autowired
    private ProtostuffMessageHandler protostuffMessageHandler;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline p = channel.pipeline();
        p.addLast(new ProtobufVarint32FrameDecoder());
        p.addLast(protostuffMessageDecoder);

        p.addLast(new ProtobufVarint32LengthFieldPrepender());
        p.addLast(protostuffMessageEncoder);

        p.addLast(protostuffMessageHandler);
    }
}
