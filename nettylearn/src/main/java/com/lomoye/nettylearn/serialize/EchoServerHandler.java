package com.lomoye.nettylearn.serialize;



import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * Created by lomoye on 2017/8/26.
 *
 */
public class EchoServerHandler extends SimpleChannelInboundHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到:" + msg);
        UserInfo userInfo = (UserInfo) msg;
    }
}
