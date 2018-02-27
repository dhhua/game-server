package com.dredh.codec.protostuff;

import com.dredh.codec.CodecInitializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtostuffHandlerInitializer extends ChannelInitializer<Channel> implements CodecInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline p = channel.pipeline();
        p.addLast(new ProtobufVarint32FrameDecoder());
        p.addLast(new ProtostuffMessageDecoder());

        p.addLast(new ProtobufVarint32LengthFieldPrepender());
        p.addLast(new ProtostuffMessageEncoder());

    }
}
