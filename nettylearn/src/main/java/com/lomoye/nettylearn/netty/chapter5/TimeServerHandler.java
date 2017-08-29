package com.lomoye.nettylearn.netty.chapter5;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * Created by lomoye on 2017/8/26.
 *
 */
public class TimeServerHandler extends SimpleChannelInboundHandler {

    private long count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;

        System.out.println("时间服务器收到数据:" + body + "|" + ++count);
        String currentTime = "QT".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
        String sp = "$_";
        currentTime += sp;

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }
}
