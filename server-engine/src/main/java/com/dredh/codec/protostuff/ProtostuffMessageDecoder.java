package com.dredh.codec.protostuff;

import com.dredh.codec.RouterMapper;
import com.dredh.constants.MessageType;
import com.dredh.handler.HandlerMapping;
import com.dredh.model.CmdHeader;
import com.dredh.model.Message;
import com.dredh.util.ProtobufUtil;
import com.dredh.util.ProtostuffSerializeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

@ChannelHandler.Sharable
public class ProtostuffMessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) {

        int length = in.readableBytes();
        byte[] array;
        int offset;
        if (in.hasArray()) {
            array = in.array();
            offset = in.arrayOffset() + in.readerIndex();
        } else {
            array = new byte[length];
            in.getBytes(in.readerIndex(), array, 0, length);
            offset = 0;
        }
        int headerLen = ProtobufUtil.readRawVarint32(in);
        int varint32Len = ProtobufUtil.computeRawVarint32Size(headerLen);

        CmdHeader header = ProtostuffSerializeUtil.decode(array, offset + varint32Len, headerLen, CmdHeader.class);
        HandlerMapping handlerMapping = RouterMapper.getInstance().getHandlerMapping(header.getRoute());

        Object parameter = null;
        if (header.getType() == MessageType.CMD && header.isHasBody() && handlerMapping.getParameterClazz() != null) {
            parameter = ProtostuffSerializeUtil.decode(
                    array,
                    offset + varint32Len + headerLen,
                    length - varint32Len - headerLen,
                    handlerMapping.getParameterClazz());
        }

        Message request = new Message(header, parameter);

        out.add(request);
    }

}
