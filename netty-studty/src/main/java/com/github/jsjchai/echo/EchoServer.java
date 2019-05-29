package com.github.jsjchai.echo;

import com.github.jsjchai.discrad.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    //端口
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    /**
     *  NioEventLoopGroup  循环处理I / O操作的多线程事件
     *  EventLoopGroup为不同类型的传输提供各种实现
     */
    public void run() throws InterruptedException {

        //接收传入的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // bossGroup接收的连接，把连接信息注册到workerGroup上
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //设置服务器的辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new EchoServerHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128)  //设置特定于Channel实现的参数
            .childOption(ChannelOption.SO_KEEPALIVE, true);//option()是提供给NioServerSocketChannel用来接收进来的连接, childOption()是提供给由父管道ServerChannel接收到的连接，

        //绑定端口并启动去接收进来的连接
        ChannelFuture channelFuture = bootstrap.bind(port).sync();

        //阻塞通道，直到socket关闭
        channelFuture.channel().closeFuture().sync();

        System.out.println("close");

        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        //telnet 127.0.0.1 10080 访问服务端
        new EchoServer(10081).run();
    }

}
