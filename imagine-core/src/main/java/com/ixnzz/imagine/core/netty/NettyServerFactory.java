package com.ixnzz.imagine.core.netty;

import com.ixnzz.imagine.core.DefaultEventType;
import com.ixnzz.imagine.core.EventResponse;
import com.ixnzz.imagine.core.EventResponseContext;
import com.ixnzz.imagine.core.channel.EventChannel;
import com.ixnzz.imagine.core.exception.ImagineException;
import com.ixnzz.imagine.core.exception.ImagineSystemError;
import com.ixnzz.imagine.core.netty.handler.ByteMessageHandler;
import com.ixnzz.imagine.core.netty.handler.DynamicLengthCodec;
import com.ixnzz.imagine.core.netty.model.NettyServerModel;
import com.ixnzz.imagine.core.serialize.Serializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * @program: imagine
 * @description: 创建Netty服务端
 * @author: 左七
 * @create: 2020-03-30 15:04
 **/
public class NettyServerFactory extends AbstractNettyFactory {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerFactory.class);

    public NettyServerFactory(Serializer serializer, Executor executor, ThreadFactory threadFactory, EventResponse eventResponse) {
        super(serializer, executor, threadFactory, eventResponse);
    }

    @Override
    public void create(byte[] content) {
        logger.debug("NettyServerFactory.create");
        NettyServerModel model = super.getSerializer().deserialize(content, NettyServerModel.class);
        if (model == null) {
            throw new ImagineException(ImagineSystemError.RPC_CREATE_CHANNEL);
        }
        getExecutor().execute(() -> doCreate(model));
    }

    private void doCreate(NettyServerModel model) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(0, super.getThreadFactory());
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("codec", new DynamicLengthCodec())
                                    .addLast("byte", new ByteMessageHandler(getEventResponse()))
                            ;
                            logger.debug("ChannelInitializer.initChannel");
                        }
                    });
            ChannelFuture future = bootstrap.bind(model.getAddress(), model.getPort()).sync();
            future.addListener((f) -> {
                if (f.isSuccess()) {
                    workGroup.execute(() -> {
                        EventResponseContext ctx = new EventResponseContext();
                        ctx.setEvent(DefaultEventType.CREATE_SUCCESS);
                        ctx.setEncrypt(false);
                        EventChannel eventChannel = new NettyEventChannel(future.channel());
                        ctx.setChannel(eventChannel);
                        getEventResponse().response(ctx);
                    });
                }
            });
            logger.info("Netty RPC Server started: {}", model.getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info("Netty RPC Server closed: {}", model.getPort());
        }

    }
}
