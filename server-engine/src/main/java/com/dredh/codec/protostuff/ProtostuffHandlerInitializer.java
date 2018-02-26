package com.dredh.codec.protostuff;

import com.dredh.codec.CodecInitializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ProtostuffHandlerInitializer extends ChannelInitializer<Channel> implements CodecInitializer {

    @Autowired
    private ProtostuffMessageDecoder protostuffMessageDecoder;

    @Autowired
    private ProtostuffMessageEncoder protostuffMessageEncoder;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline p = channel.pipeline();
        p.addLast(new ProtobufVarint32FrameDecoder());
        p.addLast(protostuffMessageDecoder);

        p.addLast(new ProtobufVarint32LengthFieldPrepender());
        p.addLast(protostuffMessageEncoder);

    }
}
