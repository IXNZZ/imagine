package com.ixnzz.imagine.core.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * @program: imagine
 * @description: TCP编码
 * @author: 左七
 * @create: 2020-03-31 14:33
 **/
public class DynamicLengthCodec extends ByteToMessageCodec<byte[]> {

    private static final int HEAD_LENGTH = 4;

    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
        if (msg == null) {
            return;
        }
        int length = msg.length;

        out.writeInt(length);
        out.writeBytes(Unpooled.wrappedBuffer(msg));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        if (in == null) {
            ctx.close();
            return;
        }

        if (in.readableBytes() < HEAD_LENGTH) {
            return;
        }

        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        byte[] body = new byte[dataLength];

        in.readBytes(body);
        out.add(ByteBufUtil.getBytes(in, in.readerIndex(), dataLength));
    }
}
