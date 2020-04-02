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
    int identity;

    DefaultEventType(int identity) {
        if (identity > 127) {
            throw new RuntimeException("event max size: 127, code: " + identity);
        }
        this.identity = identity;
    }

    @Override
    public int identity() {
        return identity;
    }

    public static EventType get(int identity) {
        EventType[] values = DefaultEventType.values();
        for (EventType e : values) {
            if (e.identity() == identity) {
                return e;
            }
        }
        return null;
    }
}
