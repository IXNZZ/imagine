package com.ixnzz.imagine.core;

public interface EventResponse {

    void response(EventResponseContext ctx);

    boolean isResponse(EventResponseContext ctx);
}
