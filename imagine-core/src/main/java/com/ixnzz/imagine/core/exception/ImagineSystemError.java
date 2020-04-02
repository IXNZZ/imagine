package com.ixnzz.imagine.core.exception;

public enum ImagineSystemError implements ImagineError {
    RPC_CREATE_CHANNEL("rpc-channel-001", "非法参数");

    String identity;

    String message;

    ImagineSystemError(String identity, String message) {
        this.identity = identity;
        this.message = message;
    }

    @Override
    public String getIdentity() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
