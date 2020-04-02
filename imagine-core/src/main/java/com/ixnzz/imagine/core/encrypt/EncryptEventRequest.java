package com.ixnzz.imagine.core.encrypt;

import com.ixnzz.imagine.core.DefaultEventType;
import com.ixnzz.imagine.core.EventRequest;
import com.ixnzz.imagine.core.EventRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description: 请求数据加密
 * @author: 左七
 * @create: 2020-03-26 14:12
 **/
public class EncryptEventRequest extends AESEncryptEvent implements EventRequest {

    private static final Logger logger = LoggerFactory.getLogger(EncryptEventRequest.class);

    public EncryptEventRequest(String encryptKey) {
        super.updateEncryptKey(encryptKey);
    }

    @Override
    public void request(EventRequestContext ctx) {
        logger.debug("encrypt request: {}", ctx.getEvent().name());
        if (ctx.isEncrypt()) {
            byte[] encrypt = super.encrypt(ctx.getBody());
            ctx.setBody(encrypt);
        }
    }

    @Override
    public boolean isRequest(EventRequestContext ctx) {
        return ctx != null && ctx.getEvent() != null && (
                ctx.getEvent().equals(DefaultEventType.REQUEST_MESSAGE)
                );
    }
}
