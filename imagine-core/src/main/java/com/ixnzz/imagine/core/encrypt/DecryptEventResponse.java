package com.ixnzz.imagine.core.encrypt;

import com.ixnzz.imagine.core.EventResponse;
import com.ixnzz.imagine.core.EventResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description: 解密 请求数据
 * @author: 左七
 * @create: 2020-03-26 14:13
 **/
public class DecryptEventResponse implements EventResponse {

    private static final Logger logger = LoggerFactory.getLogger(DecryptEventResponse.class);

    @Override
    public void response(EventResponseContext ctx) {
        logger.debug("Decrypt {}: {}",ctx.getEvent().name(), ctx.getChannel().id());
    }

    @Override
    public boolean isResponse(EventResponseContext ctx) {
        return true;
    }
}
