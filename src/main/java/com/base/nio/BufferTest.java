package com.base.nio;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.put(new String("gggggggggggggggggggggg").getBytes());
        buffer.flip();
        System.out.println(buffer.limit());
        System.out.println(new String(buffer.array()).trim());
        buffer.clear();
        buffer.put(new String("xxxxxx").getBytes());
        buffer.flip();
        System.out.println(buffer.limit());
        System.out.println(new String(buffer.array(), 0, buffer.limit()).trim());
    }
}
