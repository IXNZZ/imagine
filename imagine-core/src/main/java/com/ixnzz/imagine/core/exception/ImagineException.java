package com.ixnzz.imagine.core.exception;

/**
 * @program: imagine
 * @description: 自定义异常
 * @author: 左七
 * @create: 2020-03-26 11:21
 **/
public class ImagineException extends RuntimeException {

    private ImagineError error;

    public ImagineException(ImagineError error) {
        super(error.getIdentity() + " -> " + error.getMessage());
        this.error = error;
    }

    public ImagineError getError() {
        return error;
    }
}
