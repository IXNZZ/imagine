package com.ixnzz.imagine.core.encrypt;

/**
 * 对称加密、解密。
 */
public interface EncryptEvent {

    byte[] encrypt(byte[] content, byte[] key);

    byte[] decrypt(byte[] content, byte[] key);
}
