package com.ixnzz.imagine.core.encrypt;

public interface EncryptEvent {

    byte[] encrypt(byte[] content);

    byte[] decrypt(byte[] content);
}
