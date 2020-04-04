package com.ixnzz.imagine.core.channel;


public interface EventChannel {

    /**
     *  获得 通道ID netty 的通道ID是8位数：cab48e74
     * @return
     */
    String id();

    /**
     *  关闭通道。
     */
    void close();

    /**
     * 通道是否关闭。
     * @return
     */
    boolean isClosed();

    /**
     *  逻辑上打开通道。
     * @return
     */
    void register();

    /**
     * 通道是否注册。
     * @return
     */
    boolean isRegistered();
    /**
     * 通道是为客户端，反之为服务端。
     * @return
     */
    boolean isClient();

    /**
     * 向通道写入数据。
     * @param content - 数据内容
     */
    void write(byte[] content);
}
