package com.base.concurrent;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.IOException;

/**
 * 线程状态--小D课堂
 * @author 邱晨
 * @version 1.0
 */
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {

        //runnable --线程start之后的状态
//        Thread thread = new Thread(() -> {
//            try {
//                System.in.read();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        thread.start();

        //blocked
//        Object lock = new Object();
//        Thread thread1 = new Thread(() -> {
//            synchronized (lock) {
//                try {
//                    Thread.sleep(100000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread1.setName("thread1");
//        thread1.start();
//        Thread.sleep(2000L);
//        Thread thread2 = new Thread(() -> {
//            synchronized (lock) {
//            }
//        });
//        thread2.setName("thread2");
//        thread2.start();

        //wating
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();

    }
}
