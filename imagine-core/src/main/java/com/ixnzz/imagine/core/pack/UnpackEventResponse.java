package com.ixnzz.imagine.core.pack;

import com.ixnzz.imagine.core.*;
import com.ixnzz.imagine.core.util.IntByteUtils;

/**
 * @program: imagine
 * @description: 解包
 * @author: 左七
 * @create: 2019-11-02 19:35
 **/
public class UnpackEventResponse implements EventResponse {

    public void response(EventResponseContext ctx) {
        if (ctx == null || ctx.getBody() == null) {
            throw new NullPointerException("context body is null");
        }

        int head = IntByteUtils.byteArrayToInt(ctx.getBody());

        int bodyLength = head & PackEventRequest.MAX_BODY_LENGTH;
        if (ctx.getBody().length != bodyLength + 4) {
            throw new RuntimeException("Illegal body data");
        }

        boolean isEncrypt = (head >> 31) != 0;
        ctx.setEncrypt(isEncrypt);

        int eventCode = (head >> 24) & PackEventRequest.MAX_EVENT_SIZE;

        // 事件类型，可以不用从数据里去解。
        // 意思就是 事件类型可以后期在代码设置覆盖数据里的事件类型。
        if (ctx.getEvent() == null) {
            ctx.setEvent(DefaultEventType.get(eventCode));
        }

        if (ctx.getEvent() == null) {
            throw new NullPointerException("event is null");
        }

        byte[] body = new byte[bodyLength];

        System.arraycopy(ctx.getBody(), 4, body, 0, bodyLength);

        ctx.setBody(body);
    }

    @Override
    public boolean isResponse(EventResponseContext ctx) {
        if (ctx == null || ctx.getEvent() == null) {
            return false;
        }
        EventType event = ctx.getEvent();
        return event.value() == DefaultEventType.ACCEPT_MESSAGE.value();
    }

}
