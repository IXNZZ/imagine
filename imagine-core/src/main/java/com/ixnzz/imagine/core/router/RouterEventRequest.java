package com.ixnzz.imagine.core.router;

import com.ixnzz.imagine.core.EventRequest;
import com.ixnzz.imagine.core.EventRequestContext;
import com.ixnzz.imagine.core.observer.EventObservable;
import com.ixnzz.imagine.core.router.pool.RouterPool;
import com.ixnzz.imagine.core.router.strategy.RouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description: 请求路由。
 * @author: 左七
 * @create: 2020-03-26 14:41
 **/
public class RouterEventRequest implements EventRequest {

    private static final Logger logger = LoggerFactory.getLogger(RouterEventRequest.class);

    private RouterStrategy router;

    private EventObservable eventObservable;

    private RouterPool pool;

    @Override
    public void request(EventRequestContext ctx) {
        logger.debug("Router request: {}", ctx.getEvent().name());
    }

    @Override
    public boolean isRequest(EventRequestContext ctx) {
        return true;
    }
}
