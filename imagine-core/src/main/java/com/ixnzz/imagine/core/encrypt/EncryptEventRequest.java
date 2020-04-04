package com.ixnzz.imagine.core.encrypt;

import com.ixnzz.imagine.core.DefaultEventType;
import com.ixnzz.imagine.core.EventRequest;
import com.ixnzz.imagine.core.EventRequestContext;
import com.ixnzz.imagine.core.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description: 请求数据加密
 * @author: 左七
 * @create: 2020-03-26 14:12
 **/
public class EncryptEventRequest implements EventRequest {

    private static final Logger logger = LoggerFactory.getLogger(EncryptEventRequest.class);

    private EncryptEvent encryptEvent;

    private byte[] key;

    public EncryptEventRequest(EncryptEvent encryptEvent, byte[] key) {
        this.encryptEvent = encryptEvent;
        this.key = key;
    }

    @Override
    public void request(EventRequestContext ctx) {
        logger.debug("encrypt request: {}", ctx.getEvent().name());
        if (ctx.isEncrypt()) {
            byte[] encrypt = encryptEvent.encrypt(ctx.getBody(), key);
            ctx.setBody(encrypt);
        }
    }

    @Override
    public boolean isRequest(EventRequestContext ctx) {
        EventType event = ctx.getEvent();
        return  event.value() == DefaultEventType.REQUEST_MESSAGE.value();
    }
}
