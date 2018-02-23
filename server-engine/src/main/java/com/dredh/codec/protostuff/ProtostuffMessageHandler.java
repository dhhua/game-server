package com.dredh.codec.protostuff;

import com.dredh.codec.Message;
import com.dredh.codec.RouterMapper;
import com.dredh.model.CommandResponse;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ProtostuffMessageHandler extends SimpleChannelInboundHandler<Message> {
    @Autowired
    private RouterMapper routerMapper;

    @Override
    protected void channelRead0(ChannelHandlerContext context, Message msg) {
        Object result = routerMapper.getHandler(msg.getRoute()).handleRequest(msg);
        Schema schema = RuntimeSchema.getSchema(result.getClass());

        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        final byte[] protostuff;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(result, schema, buffer);
        } finally {
            buffer.clear();
        }

        CommandResponse response = new Message(msg.getType(), msg.getRoute(), protostuff);
        context.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
