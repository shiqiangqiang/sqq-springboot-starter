package com.sqq.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @description:
 * @author: shiqiangqiang
 * @createDate: 2020/4/20
 * @version: 1.0
 */
public class HelloNettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 定义一对线程组
        // 主线程组，用于接收客户端连接，但是不做任何处理，和老板一样不做事
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        // 从线程组，主线程组会把任务丢给他，让它里面的线程去做任务
        EventLoopGroup salveGroup = new NioEventLoopGroup();

        try {
            // netty服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(mainGroup, salveGroup)        // 设置主从线程组
                    .channel(NioServerSocketChannel.class)      // 设置nio的双向通道
                    .childHandler(new HelloServerInitializer());   // 子处理器，用于处理workergroup

            // 启动Server， 并且设置8088为启动端口，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8089).sync();

            // 监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        }finally {
            mainGroup.shutdownGracefully();
            salveGroup.shutdownGracefully();
        }

    }

}