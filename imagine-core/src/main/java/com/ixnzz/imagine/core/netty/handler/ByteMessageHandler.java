package com.ixnzz.imagine.core.netty.handler;

import com.ixnzz.imagine.core.EventResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description: 通用RPC消息接收器
 * @author: 左七
 * @create: 2020-03-31 17:25
 **/
@ChannelHandler.Sharable
public class ByteMessageHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ByteMessageHandler.class);

    private EventResponse response;

    public ByteMessageHandler(EventResponse response) {
        this.response = response;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug("ByteMessageHandler.channelRegistered: {}", ctx.channel().id().asShortText());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug("ByteMessageHandler.channelUnregistered: {}", ctx.channel().id().asShortText());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("ByteMessageHandler.channelActive: {}", ctx.channel().id().asShortText());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("ByteMessageHandler.channelInactive: {}", ctx.channel().id().asShortText());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("ByteMessageHandler.channelRead: {}", ctx.channel().id().asShortText());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("ByteMessageHandler.channelReadComplete: {}", ctx.channel().id().asShortText());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.debug("ByteMessageHandler.userEventTriggered: {}", ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.debug("ByteMessageHandler.exceptionCaught: {}", ctx.channel().id().asShortText());
    }
}
