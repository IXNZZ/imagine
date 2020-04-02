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
        EventType event = ctx.getEvent();
        if (event == null) {
            throw new RuntimeException("event type is null.");
        }
        logger.debug("start request: {}", ctx.getEvent().name());

        for (EventRequest request : events) {
            if (request.isRequest(ctx)) {
                request.request(ctx);
            }
        }
    }

    @Override
    public boolean isRequest(EventRequestContext ctx) {
        return true;
    }

    public void addEventRequest(EventRequest request) {
        events.add(request);
    }
}
