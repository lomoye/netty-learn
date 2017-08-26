package com.lomoye.nettylearn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by lomoye on 2017/8/26.
 * 基于netty实现的时间服务器
 */
public class TimeServer {

    public static void main(String[] args) throws Exception {
        new TimeServer().bind(8080);
    }


    public void bind(int port) throws Exception {
        //配置服务端的nio线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            //绑定端口等待同步
            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }
}
