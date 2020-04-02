package com.ixnzz.imagine.core.pack;

import com.ixnzz.imagine.core.DefaultEventType;
import com.ixnzz.imagine.core.EventRequest;
import com.ixnzz.imagine.core.EventRequestContext;
import com.ixnzz.imagine.core.EventType;
import com.ixnzz.imagine.core.util.IntByteUtils;

/**
 * @program: imagine
 * @description: 封装数据
 * @author: 左七
 * @create: 2019-10-23 16:01
 **/
public class PackEventRequest implements EventRequest {

    static final int MAX_BODY_LENGTH = (1 << 24) - 1;

    static final int MAX_EVENT_SIZE = (1 << 7) - 1;

    private static final int IS_ENCRYPT_INT = 1 << 31;

    @Override
    public void request(EventRequestContext ctx) {
        int bodyLength = 0;
        if (ctx.getBody() != null) {
            bodyLength = ctx.getBody().length;
            if (bodyLength > MAX_BODY_LENGTH) {
                throw new IllegalArgumentException("body length: " + bodyLength + ", max body length: " + MAX_BODY_LENGTH);
            }
        }
        int length = bodyLength;

        EventType event = ctx.getEvent();

        if (event == null) {
            throw new NullPointerException("handler event is null");
        }

        if (event.identity() > MAX_EVENT_SIZE) {
            throw new IllegalArgumentException("event size: " + event.identity() + ", max event size: " + MAX_EVENT_SIZE);
        }


        length += (event.identity() << 24);

        if (ctx.isEncrypt()) {
            length += IS_ENCRYPT_INT;
        }

        byte[] body = new byte[bodyLength + 4];
        System.arraycopy(IntByteUtils.intToByteArray(length), 0, body, 0, 4);
        System.arraycopy(ctx.getBody(), 0, body, 4, ctx.getBody().length);

        ctx.setBody(body);
    }

    @Override
    public boolean isRequest(EventRequestContext ctx) {
        return ctx != null && ctx.getEvent() != null && (
                ctx.getEvent().equals(DefaultEventType.REQUEST_MESSAGE)
        );
    }

}
