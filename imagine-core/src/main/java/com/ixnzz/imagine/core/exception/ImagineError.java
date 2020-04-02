package com.ixnzz.imagine.core.exception;

public interface ImagineError {

    /**
     * 错误 标识
     * @return
     */
    String getIdentity();

    /**
     * 错误 消息内容
     * @return
     */
    String getMessage();
}
