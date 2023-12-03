package com.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;

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
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientHandler());
        return bootstrap;
    }
}
