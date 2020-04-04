package com.ixnzz.imagine.core.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: imagine
 * @description: 加密类
 * @author: 左七
 * @create: 2020-03-26 14:00
 **/
public class AESEncryptEvent implements EncryptEvent {

    private static final Logger logger = LoggerFactory.getLogger(AESEncryptEvent.class);

    public byte[] encrypt(byte[] content, byte[] key) {
        logger.debug("encrypt: {}", new String(key));
        return content;
    }

    public byte[] decrypt(byte[] content, byte[] key) {
        logger.debug("decrypt: {}", new String(key));
        return content;
    }
}
