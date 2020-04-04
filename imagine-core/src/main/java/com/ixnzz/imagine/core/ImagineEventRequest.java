package com.ixnzz.imagine.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2019-10-23 13:34
 **/
public class ImagineEventRequest implements EventRequest {

    private static final Logger logger = LoggerFactory.getLogger(ImagineEventRequest.class);

    private List<EventRequest> events = new LinkedList<>();

    public void request(EventRequestContext ctx) {

        logger.debug("start request: {}", ctx.getEvent().name());

        for (EventRequest request : events) {
            if (request.isRequest(ctx)) {
                request.request(ctx);
            }
        }
    }

    @Override
    public boolean isRequest(EventRequestContext ctx) {
        if (ctx == null) {
            throw new NullPointerException("Request context is null.");
        }

        if (ctx.getEvent() == null) {
            throw new NullPointerException("Event type is null.");
        }
        return true;
    }

    public void addEventRequest(EventRequest request) {
        events.add(request);
    }
}
