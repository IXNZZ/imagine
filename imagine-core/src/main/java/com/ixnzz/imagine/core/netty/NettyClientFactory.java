package com.ixnzz.imagine.core.netty;

import com.ixnzz.imagine.core.DefaultEventType;
import com.ixnzz.imagine.core.EventResponse;
import com.ixnzz.imagine.core.EventResponseContext;
import com.ixnzz.imagine.core.channel.EventChannel;
import com.ixnzz.imagine.core.exception.ImagineException;
import com.ixnzz.imagine.core.exception.ImagineSystemError;
import com.ixnzz.imagine.core.netty.handler.ByteMessageHandler;
import com.ixnzz.imagine.core.netty.handler.DynamicLengthCodec;
import com.ixnzz.imagine.core.netty.model.NettyClientModel;
import com.ixnzz.imagine.core.serialize.Serializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * @program: imagine
 * @description: 创建Netty客户端
 * @author: 左七
 * @create: 2020-03-30 15:05
 **/
public class NettyClientFactory extends AbstractNettyFactory {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientFactory.class);

    public NettyClientFactory(Serializer serializer, Executor executor, ThreadFactory threadFactory, EventResponse eventResponse) {
        super(serializer, executor, threadFactory, eventResponse);
    }

    @Override
    public void create(byte[] content) {
        logger.debug("");
        NettyClientModel model = super.getSerializer().deserialize(content, NettyClientModel.class);
        if (model == null) {
            throw new ImagineException(ImagineSystemError.RPC_CREATE_CHANNEL);
        }

        super.getExecutor().execute(() -> coConnect(model));
    }

    private void coConnect(NettyClientModel model) {

        EventLoopGroup workerGroup = new NioEventLoopGroup(0, super.getThreadFactory());
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("codec", new DynamicLengthCodec())
                                    .addLast("byte", new ByteMessageHandler(getEventResponse()));
                        }
                    });
            if (model.getLocalAddress() != null
                    && !model.getLocalAddress().equals("")
                    && model.getLocalPort() > 0) {
                bootstrap.localAddress(model.getLocalAddress(), model.getLocalPort());
            }

            ChannelFuture connect = bootstrap.connect(model.getAddress(), model.getPort()).sync();
            connect.addListener(future -> {
                if (future.isSuccess()) {
                    workerGroup.execute(() -> {
                        EventResponseContext ctx = new EventResponseContext();
                        ctx.setEvent(DefaultEventType.CONNECT_SUCCESS);
                        ctx.setEncrypt(false);
                        EventChannel eventChannel = new NettyEventChannel(connect.channel());
                        ctx.setChannel(eventChannel);
                        getEventResponse().response(ctx);
                    });
                }
            });
            logger.info("Netty RPC Client started: {}", model.getPort());
            connect.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            logger.info("Netty RPC Client closed: {}", model.getPort());
        }
    }
}
