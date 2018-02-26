package com.dredh.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtostuffSerializeUtil {

    public static<T> T decode(byte[] array, int offset, int length, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T result = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(array, offset, length, result, schema);
        return result;
    }

    public static byte[] encode(Object src) {
        if (src == null) {
            return null;
        }
        Schema schema = RuntimeSchema.getSchema(src.getClass());
        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        final byte[] result;
        try {
            result = ProtostuffIOUtil.toByteArray(src, schema, buffer);
        } finally {
            buffer.clear();
        }

        return result;
    }
}
