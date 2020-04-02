package com.ixnzz.imagine.core.netty.model;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-31 16:09
 **/
public class NettyClientModel extends NettyModel {
    private String localAddress;

    private int localPort;

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }
}
