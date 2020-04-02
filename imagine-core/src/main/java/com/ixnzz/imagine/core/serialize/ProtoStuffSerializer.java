package com.ixnzz.imagine.core.serialize;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @program: imagine
 * @description:
 * @author: 左七
 * @create: 2020-03-30 15:47
 **/
public class ProtoStuffSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T source) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] result = null;
        try {
            RuntimeSchema<T> schema = RuntimeSchema.createFrom((Class<T>) source.getClass());
            result = ProtostuffIOUtil.toByteArray(source, schema, buffer);
        } finally {
            buffer.clear();
        }
        return result;
    }

    @Override
    public <T> T deserialize(byte[] source, Class<T> clazz) {
        RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
        T instance;
        try {
            instance = clazz.newInstance();
            ProtostuffIOUtil.mergeFrom(source, instance, schema);
        } catch (Exception e) {
            throw new RuntimeException("deserialize exception", e);
        }
        return instance;
    }
}
