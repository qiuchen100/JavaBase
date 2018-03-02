package com.base.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNio {
    public static void main(String[] args) throws IOException {
        RandomAccessFile ras = new RandomAccessFile("d:\\ras.txt", "rw");
        FileChannel channel = ras.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("FILE NIO Test".getBytes());
        buffer.flip();
        channel.write(buffer);
        channel.close();
    }
}
