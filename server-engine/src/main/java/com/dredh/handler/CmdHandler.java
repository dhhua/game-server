package com.dredh.handler;

import com.dredh.codec.RouterMapper;
import com.dredh.constants.MessageType;
import com.dredh.model.CmdHeader;
import com.dredh.model.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ChannelHandler.Sharable
@Component
public class CmdHandler extends SimpleChannelInboundHandler<Message> {

    @Autowired
    private RouterMapper routerMapper;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message request) throws Exception {
        CmdHeader header = request.getHeader();
        HandlerMapping handlerMapping = routerMapper.getHandlerMapping(header.getRoute());
        Object result = handlerMapping.getCommandMethod().invoke(handlerMapping.getHandler(), request.getBody());

        CmdHeader responseHeader = new CmdHeader(MessageType.RESPONSE, header.getRoute(), true);
        ctx.writeAndFlush(new Message(responseHeader, result));
    }
}
