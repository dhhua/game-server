package com.dredh;

import com.dredh.codec.protostuff.ProtostuffMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GameClientHandler extends SimpleChannelInboundHandler<ProtostuffMessage> {

    private ChannelHandlerContext ctx;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx = ctx;

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtostuffMessage protostuffMessage) throws Exception {
        System.out.println(protostuffMessage);
    }
}
