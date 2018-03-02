package com.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

class Client implements Runnable {

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            Selector selector = Selector.open();
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress(5080));
            sc.register(selector, SelectionKey.OP_CONNECT);
            while (true) {
                selector.select();
                Iterator it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    if (key.isConnectable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 如果正在连接，则完成连接
                        if(channel.isConnectionPending()){
                            channel.finishConnect();
                        }
                        // 设置成非阻塞
                        channel.configureBlocking(false);

                        //在这里可以给服务端发送信息哦
                        buffer.clear();
                        buffer.put(new String("XXX").getBytes());
                        buffer.flip();
                        channel.write(buffer);

                        System.out.println("客户端发送了一条消息：" + new String(buffer.array()).trim());
                        //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.configureBlocking(false);
                        buffer.clear();
                        socketChannel.read(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Server implements Runnable {

    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.socket().bind(new InetSocketAddress(5080));
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Set selectedKeys = selector.selectedKeys();
                Iterator it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssChannel.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        it.remove();
                    } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        buffer.clear();
                        sc.read(buffer);
                        buffer.flip();
                        String msg = new String(buffer.array()).trim();
                        //System.out.println(msg.length());
                        System.out.println("服务器端收到消息：" + msg);
                        buffer.clear();
                        if (msg.equals("FTP")) {
                            buffer.put(new String("已收到反馈！ ").getBytes());
                        } else {
                            buffer.put(new String("不明消息！ ").getBytes());
                        }
                        buffer.flip();
                        sc.write(buffer);
                        it.remove();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class NioTest {
    public static void main(String[] args) throws IOException {
        new Thread(new Server()).start();
        new Thread(new Client()).start();
    }
}
