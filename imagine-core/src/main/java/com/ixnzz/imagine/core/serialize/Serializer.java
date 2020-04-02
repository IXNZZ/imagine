package com.ixnzz.imagine.core.serialize;

public interface Serializer {

    <T> byte[] serialize(T source);

    <T> T deserialize(byte[] source, Class<T> clazz);
}
