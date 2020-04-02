package com.ixnzz.imagine.core;

/**
 * RPC（事件）类型
 */
public enum DefaultEventType implements EventType {
    /**
     * 请求创建 RPC 服务。
     */
    CREATE_CHANNEL(1),
    /**
     * 请求连接 RPC 服务。
     */
    CONNECT_CHANNEL(2),
    /**
     * 创建 RPC 服务成功。
     */
    CREATE_SUCCESS(3),
    /**
     * 连接 RPC 服务成功。
     */
    CONNECT_SUCCESS(4),
    /**
     * 请求 关闭 RPC 服务。
     */
    CLOSE_CHANNEL(5),
    /**
     * 关闭 RPC 服务成功。
     */
    CLOSE_SUCCESS(6),
    /**
     * 请求发送 RPC 消息。
     */
    REQUEST_MESSAGE(7),
    /**
     *  接收到 RPC 消息。
     */
    ACCEPT_MESSAGE(8)
    ;

    /**
     *  最大 127
     */
    int value;

    DefaultEventType(int value) {
        if (value > 127) {
            throw new RuntimeException("event max size: 127, code: " + value);
        }
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }

    public static EventType get(int identity) {
        EventType[] values = DefaultEventType.values();
        for (EventType e : values) {
            if (e.value() == identity) {
                return e;
            }
        }
        return null;
    }
}
