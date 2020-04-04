package com.ixnzz.imagine.core.netty;

import com.ixnzz.imagine.core.channel.EventChannel;
import com.ixnzz.imagine.core.exception.ChannelClosedException;
import io.netty.channel.Channel;

/**
 * @program: imagine
 * @description: Netty的通道
 * @author: 左七
 * @create: 2020-03-26 14:21
 **/
public class NettyEventChannel implements EventChannel {

    private Channel channel;

    private boolean client;

    private volatile boolean register = false;

    public NettyEventChannel(Channel channel, boolean isClient) {
        this.channel = channel;
        this.client = isClient;
    }


    @Override
    public String id(){
        if (isClosed()) {
            throw new ChannelClosedException("Channel closed.");
        }
        return channel.id().asShortText();
    }

    @Override
    public void close() {
        if (!isClosed())
            channel.close();
    }

    @Override
    public boolean isClosed() {
        return channel == null || !channel.isOpen();
    }

    @Override
    public void register() {
        register = true;
    }

    @Override
    public boolean isRegistered() {
        return register;
    }

    @Override
    public boolean isClient() {
        return client;
    }

    @Override
    public void write(byte[] content) {
        if (isClosed()) {
            throw new ChannelClosedException("Channel closed.");
        }
        channel.writeAndFlush(content);
    }
}
