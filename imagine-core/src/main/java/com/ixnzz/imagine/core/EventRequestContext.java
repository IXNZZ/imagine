package com.ixnzz.imagine.core;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2019-10-23 13:26
 **/
public class EventRequestContext {
    /**
     * 事件类型
     */
    private EventType event;

    /**
     * 接收目标
     */
    private EventTarget target;

    /**
     * 是否加密
     */
    private boolean encrypt = false;

    /**
     * 数据内容
     */
    private byte[] body;

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

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public EventTarget getTarget() {
        return target;
    }

    public void setTarget(EventTarget target) {
        this.target = target;
    }
}
