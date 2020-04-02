package com.ixnzz.imagine.core;

import com.ixnzz.imagine.core.channel.ChannelEventResponse;
import com.ixnzz.imagine.core.consumer.ConsumerEventResponse;
import com.ixnzz.imagine.core.encrypt.DecryptEventResponse;
import com.ixnzz.imagine.core.encrypt.EncryptEventRequest;
import com.ixnzz.imagine.core.netty.NettyEventRequest;
import com.ixnzz.imagine.core.pack.PackEventRequest;
import com.ixnzz.imagine.core.pack.UnpackEventResponse;
import com.ixnzz.imagine.core.router.RouterEventRequest;
import com.ixnzz.imagine.core.serialize.ProtoStuffSerializer;
import com.ixnzz.imagine.core.serialize.Serializer;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *  事件请求构建器。
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-26 11:05
 **/
public class EventBuilder {

    private List<EventRequest> requests = new LinkedList<>();

    private Consumer<EventResponseContext> messageConsumer;

    private String encryptKey;

    private Serializer serializer;

    private EventBuilder() {
        serializer = new ProtoStuffSerializer();
    }

    public static EventBuilder newBuilder() {
        return new EventBuilder();
    }

    public EventRequest build() {
        ImagineEventRequest request = new ImagineEventRequest();
        for (EventRequest r : requests) {
            request.addEventRequest(r);
        }

        ImagineEventResponse response = new ImagineEventResponse();
        response.addResponse(new UnpackEventResponse());
        response.addResponse(new ChannelEventResponse());
        response.addResponse(new DecryptEventResponse());
        response.addResponse(new ConsumerEventResponse(messageConsumer));

        request.addEventRequest(new EncryptEventRequest(this.encryptKey));
        request.addEventRequest(new NettyEventRequest(response, serializer));
        request.addEventRequest(new PackEventRequest());
        request.addEventRequest(new RouterEventRequest());
        return request;
    }

    public EventBuilder addRequest(EventRequest request) {
        if (request != null) {
            requests.add(request);
        }
        return this;
    }

    public EventBuilder consumer(Consumer<EventResponseContext> consumer) {
        this.messageConsumer = consumer;
        return this;
    }

    public EventBuilder encryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
        return this;
    }

    public EventBuilder serialize(Serializer serializer) {
        if (serializer != null) {
            this.serializer = serializer;
        }
        return this;
    }
}
