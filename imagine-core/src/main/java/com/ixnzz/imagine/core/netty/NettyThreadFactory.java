package com.ixnzz.imagine.core.netty;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-31 13:42
 **/
public class NettyThreadFactory implements ThreadFactory {


    private static final AtomicInteger supperNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final String namePrefix;
    private boolean isDaemon;

    public NettyThreadFactory() {
        this("netty");
    }

    public NettyThreadFactory(String name) {
        this(name, false);
    }

    public NettyThreadFactory(String prefix, boolean daemon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = prefix + "-" + supperNumber.getAndIncrement() + "-thread-";
        isDaemon = daemon;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
