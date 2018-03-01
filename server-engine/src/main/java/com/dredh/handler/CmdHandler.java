package com.dredh.handler;

import com.dredh.codec.RouterMapper;
import com.dredh.constants.MessageType;
import com.dredh.model.CmdHeader;
import com.dredh.model.Message;
import com.dredh.server.ServerLaunder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@ChannelHandler.Sharable
@Component
@ConditionalOnBean(ServerLaunder.class)
public class CmdHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message request) throws Exception {
        CmdHeader header = request.getHeader();
        //TODO 封装
        switch (header.getType()) {
            case MessageType.HEART_BEAT: return;
            case MessageType.CMD: {
                HandlerMapping mapping = RouterMapper.getInstance().getHandlerMapping(header.getRoute());
                Object result = mapping.getCommandMethod().invoke(mapping.getHandler(), request.getBody());

                CmdHeader responseHeader = new CmdHeader(MessageType.CMD, "response." + header.getRoute(), true);
                ctx.writeAndFlush(new Message(responseHeader, result));
                break;
            }
            case MessageType.SYSTEM: {
                HandlerMapping mapping = RouterMapper.getInstance().getHandlerMapping(header.getRoute());
                Object result = mapping.getCommandMethod().invoke(mapping.getHandler(), request.getBody(), ctx.channel());

                CmdHeader responseHeader = new CmdHeader(MessageType.CMD, "response." + header.getRoute(), true);
                ctx.writeAndFlush(new Message(responseHeader, result));
                break;
            }
        }

    }
}
