package com.ixnzz.imagine.core.exception;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-04-02 22:37
 **/
public class ChannelClosedException extends RuntimeException {

    public ChannelClosedException(String message) {
        super(message);
    }
}
