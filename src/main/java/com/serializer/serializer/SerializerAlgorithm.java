package com.serializer.serializer;


public interface SerializerAlgorithm {
    /**
     * json 序列化标识
     */
    byte JSON = 1;

    /**
     * protostuff 序列化标识
     */
    byte PROTO_STUFF = 2;

    /**
     * kryo 序列化标识
     */
    byte KRYO = 3;
}
