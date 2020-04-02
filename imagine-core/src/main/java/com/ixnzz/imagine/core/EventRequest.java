package com.ixnzz.imagine.core;

public interface EventRequest {

    void request(EventRequestContext ctx);

    boolean isRequest(EventRequestContext ctx);
}
