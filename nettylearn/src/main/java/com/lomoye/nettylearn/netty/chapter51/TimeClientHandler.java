package com.lomoye.nettylearn.netty.chapter51;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lomoye on 2017/8/26.
 *
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private long count = 0L;

    byte[] req;

    public TimeClientHandler() {
        req = "QT".getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf firstMessage = null;

        for (int i = 0; i < 100; i++) {
            firstMessage = Unpooled.buffer(req.length);
            firstMessage.writeBytes(req);
            ctx.writeAndFlush(firstMessage);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;

        System.out.println(body + "|" + ++count);
    }
}
