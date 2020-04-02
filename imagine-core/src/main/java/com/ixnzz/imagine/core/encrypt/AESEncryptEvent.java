package com.ixnzz.imagine.core.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description: 加密类
 * @author: 左七
 * @create: 2020-03-26 14:00
 **/
public abstract class AESEncryptEvent implements EncryptEvent {

    private static final Logger logger = LoggerFactory.getLogger(AESEncryptEvent.class);

    private String encryptKey;

    public byte[] encrypt(byte[] content) {
        logger.debug("encrypt: {}", encryptKey);
        return content;
    }

    public byte[] decrypt(byte[] content) {
        logger.debug("decrypt: {}", encryptKey);
        return content;
    }

    protected synchronized void updateEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }
}
