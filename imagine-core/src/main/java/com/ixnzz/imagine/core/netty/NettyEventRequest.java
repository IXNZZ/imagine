package com.ixnzz.imagine.core.netty;

import com.ixnzz.imagine.core.DefaultEventType;
import com.ixnzz.imagine.core.EventRequest;
import com.ixnzz.imagine.core.EventRequestContext;
import com.ixnzz.imagine.core.EventResponse;
import com.ixnzz.imagine.core.serialize.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @program : imagine
 * @description : Netty 连接
 * @author : 左七
 * @create: 2020-03-26 14:14
 **/
public class NettyEventRequest implements EventRequest {

    private static final Logger logger = LoggerFactory.getLogger(NettyEventRequest.class);

    static final ThreadFactory DEFAULT_THREAD_FACTORY = new NettyThreadFactory();

    private EventResponse response;

    private Serializer serialize;

    public NettyEventRequest(EventResponse response, Serializer serialize) {
        this.response = response;
        this.serialize = serialize;
    }

    @Override
    public void request(EventRequestContext ctx) {
        logger.debug("NettyEventRequest.request");
        AbstractNettyFactory factory = getFactory(ctx);
        if (factory != null) {
            factory.create(ctx.getBody());
        }
    }

    @Override
    public boolean isRequest(EventRequestContext ctx) {
        return ctx != null && ctx.getEvent() != null && (
                ctx.getEvent().equals(DefaultEventType.CREATE_CHANNEL)
                || ctx.getEvent().equals(DefaultEventType.CONNECT_CHANNEL)
        );
    }

    /**
     *  事件选择。只处理 CONNECT_CHANNEL 和 CREATE_CHANNEL
     * @param ctx
     * @return
     */
    private AbstractNettyFactory getFactory(EventRequestContext ctx) {
        if (DefaultEventType.CONNECT_CHANNEL.equals(ctx.getEvent())) {
            return new NettyClientFactory(this.serialize,
                    Executors.newSingleThreadExecutor(DEFAULT_THREAD_FACTORY),
                    DEFAULT_THREAD_FACTORY,
                    response);
        } else if (DefaultEventType.CREATE_CHANNEL.equals(ctx.getEvent())) {
            return new NettyServerFactory(this.serialize,
                    Executors.newSingleThreadExecutor(DEFAULT_THREAD_FACTORY),
                    DEFAULT_THREAD_FACTORY,
                    response);
        }
        return null;
    }
}
