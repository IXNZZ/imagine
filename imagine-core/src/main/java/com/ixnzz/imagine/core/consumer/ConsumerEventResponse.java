package com.ixnzz.imagine.core.consumer;

import com.ixnzz.imagine.core.EventResponse;
import com.ixnzz.imagine.core.EventResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-26 16:19
 **/
public class ConsumerEventResponse implements EventResponse {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerEventResponse.class);

    private Consumer<EventResponseContext> consumer;

    public ConsumerEventResponse(Consumer<EventResponseContext> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void response(EventResponseContext ctx) {
        logger.debug("Consumer {}: {}",ctx.getEvent().name(), ctx.getChannel().id());
        if (consumer != null) {
            consumer.accept(ctx);
        }
    }

    @Override
    public boolean isResponse(EventResponseContext ctx) {
        return true;
    }
}
