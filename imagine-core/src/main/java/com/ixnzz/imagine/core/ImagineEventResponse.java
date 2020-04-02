package com.ixnzz.imagine.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-26 16:04
 **/
public class ImagineEventResponse implements EventResponse {

    private static final Logger logger = LoggerFactory.getLogger(ImagineEventResponse.class);

    private List<EventResponse> responses = new LinkedList<>();

    @Override
    public void response(EventResponseContext ctx) {
        logger.debug("{}: {}",ctx.getEvent().name(), ctx.getChannel().id());
        for (EventResponse response : responses) {
            if (response.isResponse(ctx)) {
                response.response(ctx);
            }
        }
    }

    @Override
    public boolean isResponse(EventResponseContext ctx) {
        return true;
    }

    public void addResponse(EventResponse response) {
        responses.add(response);
    }
}
