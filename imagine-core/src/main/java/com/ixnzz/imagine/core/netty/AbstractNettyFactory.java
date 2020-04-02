package com.ixnzz.imagine.core.netty;

import com.ixnzz.imagine.core.EventResponse;
import com.ixnzz.imagine.core.serialize.Serializer;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * @program: imagine
 * @description: 创建Netty 连接。
 * @author: 左七
 * @create: 2020-03-30 15:01
 **/
public abstract class AbstractNettyFactory {

    private Serializer serializer;

    private Executor executor;

    private ThreadFactory threadFactory;

    private EventResponse eventResponse;

    AbstractNettyFactory(Serializer serializer, Executor executor, ThreadFactory threadFactory, EventResponse eventResponse) {
        this.serializer = serializer;
        this.executor = executor;
        this.threadFactory = threadFactory;
        this.eventResponse = eventResponse;
    }

    public abstract void create(byte[] content);

    Serializer getSerializer() {
        return serializer;
    }

    Executor getExecutor() {
        return executor;
    }

    ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    EventResponse getEventResponse() {
        return eventResponse;
    }
}
