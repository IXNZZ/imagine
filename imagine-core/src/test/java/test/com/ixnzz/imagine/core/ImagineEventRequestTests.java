package test.com.ixnzz.imagine.core;

import com.ixnzz.imagine.core.*;
import com.ixnzz.imagine.core.netty.model.NettyClientModel;
import com.ixnzz.imagine.core.netty.model.NettyServerModel;
import com.ixnzz.imagine.core.serialize.ProtoStuffSerializer;
import com.ixnzz.imagine.core.serialize.Serializer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-26 16:35
 **/
public class ImagineEventRequestTests {

    private static final Serializer serialize = new ProtoStuffSerializer();

    private String remoteAddress = "127.0.0.1";

    private int remotePort = 8080;

    @BeforeClass
    public static void beforeClass() {
        //System.setProperty("log.dir", "./logs");
    }

    public void before(EventRequest request) {
        NettyServerModel model = new NettyServerModel();
        model.setAddress(remoteAddress);
        model.setPort(remotePort);
        byte[] content = ImagineEventRequestTests.serialize.serialize(model);
        //创建测试服务端
        EventRequestContext ctx = new EventRequestContext();
        ctx.setEvent(DefaultEventType.CREATE_CHANNEL);
        ctx.setBody(content);
        request.request(ctx);
    }

    @Test
    public void testRequest() throws InterruptedException {
        System.out.println("ImagineEventRequestTests.testRequest 1");

        EventRequest request = EventBuilder.newBuilder()
                .encryptKey("testKey")
                .consumer((c) -> {
                    if (c.getEvent().identity() == DefaultEventType.CONNECT_SUCCESS.identity()) {
                        System.out.println("ImagineEventRequestTests.testRequest 2");
                        c.getChannel().close();
                    }
                })
                .build();
        before(request);

        System.out.println("ImagineEventRequestTests.testRequest 3");
        NettyClientModel model = new NettyClientModel();
        model.setAddress(remoteAddress);
        model.setPort(remotePort);
        byte[] content = ImagineEventRequestTests.serialize.serialize(model);

        EventRequestContext ctx = new EventRequestContext();
        ctx.setEvent(DefaultEventType.CONNECT_CHANNEL);
        ctx.setBody(content);
        request.request(ctx);

        Thread.sleep(10 * 1000);
    }
}
