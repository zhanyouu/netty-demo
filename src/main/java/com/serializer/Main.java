package com.serializer;


import com.serializer.client.LiClient;
import com.serializer.common.MsgRepository;
import com.serializer.protocal.codec.PacketCodeC;
import com.serializer.protocal.packet.MsgPacket;
import com.serializer.server.ZhangServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import static com.serializer.client.LiClient.*;
import static com.serializer.common.MsgConstant.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = ZhangServer.bootstrap();
        ChannelFuture serverChannelFuture = ZhangServer.bind(serverBootstrap, PORT);
        Bootstrap clientBootstrap = bootstrap();
        ChannelFuture clientChannelFuture = connect(clientBootstrap, HOST, PORT, MAX_RETRY);
        Thread.sleep(1000);
        for (int i = 0; i < 1; i++) {
            MsgPacket one = MsgRepository.getInstance().getZhangMsgPacket(MSG_SESSION_ONE);
            MsgPacket two = MsgRepository.getInstance().getLiMsgPacket(MSG_SESSION_TWO);
            MsgPacket three = MsgRepository.getInstance().getLiMsgPacket(MSG_SESSION_THREE);
            sendMsg(ZhangServer.getChannel(HOST), one);
            sendMsg(clientChannelFuture.channel(), two);
            sendMsg(clientChannelFuture.channel(), three);
        }
    }

    private static void sendMsg(Channel channel, MsgPacket packet) {
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(packet);
        channel.writeAndFlush(byteBuf);
    }
}
