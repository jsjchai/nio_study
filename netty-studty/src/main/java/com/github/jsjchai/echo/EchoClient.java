package com.github.jsjchai.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EchoClient {

    private String host;
    private int port;
    private int size;

    public void run() throws InterruptedException {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new EchoClientHandler(size));
                }
            });

        // Start the client.
        ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

        // Wait until the connection is closed.
        channelFuture.channel().closeFuture().sync();

        // Shut down the event loop to terminate all threads.
        group.shutdownGracefully();


    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("127.0.0.1",10081,1024).run();
    }
}