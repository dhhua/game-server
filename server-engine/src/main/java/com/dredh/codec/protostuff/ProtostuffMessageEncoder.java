package com.dredh.codec.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@ChannelHandler.Sharable
@Component
public class ProtostuffMessageEncoder extends MessageToMessageEncoder<ProtostuffMessage> {

    @Override
    protected void encode(ChannelHandlerContext context, ProtostuffMessage protostuffMessage, List<Object> out) throws Exception {

        Schema<ProtostuffMessage> schema = RuntimeSchema.getSchema(ProtostuffMessage.class);

        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        final byte[] protostuff;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(protostuffMessage, schema, buffer);
        } finally {
            buffer.clear();
        }

        out.add(Unpooled.wrappedBuffer(protostuff));

    }
}
