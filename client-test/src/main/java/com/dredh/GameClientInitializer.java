package com.dredh;

import com.dredh.codec.protostuff.ProtostuffMessageDecoder;
import com.dredh.codec.protostuff.ProtostuffMessageEncoder;
import com.dredh.handler.CmdHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class GameClientInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline p = channel.pipeline();
        p.addLast(new ProtobufVarint32FrameDecoder());
        p.addLast(new ProtostuffMessageDecoder());
        p.addLast(new CmdHandler());
        p.addLast(new ProtobufVarint32LengthFieldPrepender());
        p.addLast(new ProtostuffMessageEncoder());

        p.addLast(new GameClientHandler());
    }
}
