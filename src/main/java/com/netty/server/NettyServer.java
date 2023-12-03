package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;


public class NettyServer {
    private static final int PORT = 9999;


    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = bootstrap();
        bootstrap.bind().sync();
    }

    public static ServerBootstrap bootstrap() {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(PORT))/*指定服务器监听端口*/
                .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ServerHandler());
                return bootstrap;
    }

}
