package com.github.jsjchai.discrad;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * The most simplistic protocol in the world is not 'Hello, World!' but DISCARD. It's a protocol
 * that discards any received data without any response. 服务器端处理通道
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        // 打印客户端输入，传输过来的的字符
        System.out.println(in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常，并关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
