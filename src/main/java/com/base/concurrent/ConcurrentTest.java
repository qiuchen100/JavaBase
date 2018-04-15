package com.base.concurrent;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConcurrentTest {
    static Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
//    static HashMap<String, String> map = new HashMap<String, String>();
    static class MyThread extends Thread {
        String name;

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                map.put(name + i, i + "");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyThread thread1 = new MyThread("AA");
        MyThread thread2 = new MyThread("BB");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(map.size());
    }
}
