package com.dredh.codec.protostuff;

import com.dredh.model.Message;
import com.dredh.util.ProtobufUtil;
import com.dredh.util.ProtostuffSerializeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@ChannelHandler.Sharable
public class ProtostuffMessageEncoder extends MessageToByteEncoder<Message> {

    static void writeRawVarint32(ByteBuf out, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                out.writeByte(value);
                return;
            } else {
                out.writeByte((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
        byte[] header = ProtostuffSerializeUtil.encode(message.getHeader());
        int headerLen = ProtobufUtil.computeRawVarint32Size(header.length);
        writeRawVarint32(out, header.length);
        if (message.getBody() != null) {
            byte[] body = ProtostuffSerializeUtil.encode(message.getBody());
            out.ensureWritable(headerLen + header.length + body.length);
            out.writeBytes(header);
            out.writeBytes(body);
        } else {
            out.ensureWritable(headerLen + header.length);
            out.writeBytes(header);
        }
    }

}
