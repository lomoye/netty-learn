package com.lomoye.nettylearn.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by lomoye on 2017/9/2.
 * msgpack解码器
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte[] array;

        final int length = msg.readableBytes();

        array = new byte[length];

        msg.getBytes(msg.readerIndex(), array, 0, length);

        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(array));
    }
}
