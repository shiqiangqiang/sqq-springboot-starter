package com.sqq.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @description: 初始化器，channel注册后，会执行里面相应的初始化方法
 * @author: shiqiangqiang
 * @createDate: 2020/4/20
 * @version: 1.0
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 通过SocketChannel获取对应的管道
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 通过管道添加Handler
        // HttpServerCodec是有netty自己提供的助手类，可以理解为拦截器
        // 当请求到达服务器，我们需要走解码，相应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        // 添加自定义助手类，返回“Hello Netty...”
        pipeline.addLast("customHandler", new CustomHandler());
    }
}