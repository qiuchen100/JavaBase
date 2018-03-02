package com.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;

class NIOClient implements Runnable{
    Selector selector;
    ByteBuffer buffer = ByteBuffer.allocate(48);
    public void initClient() {
        try {
            this.selector = Selector.open();
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("127.0.0.1", 5667));
            sc.register(selector, SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            try {
                this.selector.select();
                Iterator iterator = this.selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                    SelectionKey key = (SelectionKey) iterator.next();
                    if (key.isConnectable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                        }
                        channel.configureBlocking(false);
                        buffer.clear();
                        buffer.put("我是客户端！！！".getBytes());
                        buffer.flip();
                        channel.write(buffer);
                        channel.register(this.selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        channel.configureBlocking(false);
                        buffer.clear();
                        channel.read(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array(),0, buffer.limit()).trim());
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        initClient();
        listen();
    }
}

class NIOServer implements Runnable{
    Selector selector;
    ByteBuffer buffer = ByteBuffer.allocate(48);
    public void initServer() {
        try {
            this.selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 5667));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();

                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    //ssc.configureBlocking(false);
                    SocketChannel channel = ssc.accept();
                    channel.configureBlocking(false);
                    buffer.clear();
                    buffer.put("我是服务端！！！".getBytes());
                    buffer.flip();
                    buffer.clear();
                    buffer.put("xxx".getBytes());
                    buffer.flip();
                    channel.write(buffer);
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.configureBlocking(false);
                    buffer.clear();
                    channel.read(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(),0, buffer.limit()).trim());
                    //System.out.println(new String(Arrays.copyOf(buffer.array(), buffer.limit())));
                }
                iterator.remove();
            }
        }
    }
    @Override
    public void run() {
        initServer();
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class NIOSocket {
    public static void main(String[] args) {
        new Thread(new NIOClient()).start();
        new Thread(new NIOServer()).start();
    }
}
