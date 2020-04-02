package com.ixnzz.imagine.core;

import com.ixnzz.imagine.core.channel.EventChannel;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-26 11:06
 **/
public class EventResponseContext {

    private EventChannel channel;

    private byte[] body;

    private EventType event;

    private boolean encrypt;

    public EventChannel getChannel() {
        return channel;
    }

    public void setChannel(EventChannel channel) {
        this.channel = channel;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public EventType getEvent() {
        return event;
    }

    public void setEvent(EventType event) {
        this.event = event;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }
}
