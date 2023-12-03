package com.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 9999;


    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = NettyClient.bootstrap();
        /*连接到远程节点，阻塞直到连接完成*/
        ChannelFuture f = bootstrap.connect().sync();
        /*阻塞程序，直到Channel发生了关闭*/
        f.channel().closeFuture().sync();
    }

    public static Bootstrap bootstrap() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(HOST, PORT))
                .handler(new ClientHandler());
        return bootstrap;
    }
}
