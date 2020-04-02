package com.ixnzz.imagine.core.channel;

import com.ixnzz.imagine.core.EventResponse;
import com.ixnzz.imagine.core.EventResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-26 16:17
 **/
public class ChannelEventResponse implements EventResponse {

    private static final Logger logger = LoggerFactory.getLogger(ChannelEventResponse.class);

    @Override
    public void response(EventResponseContext ctx) {
        logger.debug("Channel {}: {}",ctx.getEvent().name(), ctx.getChannel().id());
    }

    @Override
    public boolean isResponse(EventResponseContext ctx) {
        return true;
    }
}
