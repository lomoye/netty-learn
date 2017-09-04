package com.lomoye.nettylearn.serialize;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lomoye on 2017/8/26.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 100;

    byte[] req;

    public EchoClientHandler() {
        String separator = System.getProperty("line.separator");
        req = ("QT" + separator).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] userInfos = mockUserInfo();

        for (UserInfo userInfo : userInfos) {
            ctx.write(userInfo);
        }

        ctx.flush();
    }

    private UserInfo[] mockUserInfo() {
        UserInfo[] userInfos = new UserInfo[count];

        for (int i = 0; i < count; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("LOMOYE" + i);
            userInfos[i] = userInfo;
        }

        return userInfos;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("echoClientHandler receive:" + msg);
        ctx.write(msg);
    }
}
