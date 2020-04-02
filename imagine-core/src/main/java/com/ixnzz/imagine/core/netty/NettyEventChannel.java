package com.ixnzz.imagine.core.netty;

import com.ixnzz.imagine.core.channel.EventChannel;
import io.netty.channel.Channel;

/**
 * @program: imagine
 * @description: Netty的通道
 * @author: 左七
 * @create: 2020-03-26 14:21
 **/
public class NettyEventChannel implements EventChannel {

    private Channel channel;

    public NettyEventChannel(Channel channel) {
        this.channel = channel;
    }


    @Override
    public String id() {
        return channel.id().asShortText();
    }

    @Override
    public void close() {
        channel.close();
    }
}
