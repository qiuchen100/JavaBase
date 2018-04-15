package com.base.threadbase;

/**
 *  初步尝试多线程
 *  @author 邱晨
 *  @version 1.0
 */
public class ThreadTest {
    static class MyThread extends Thread {
        private String name;
        public MyThread(String name) {
            super(name);
            this.name = name;
        }
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 50; i++) {
                System.out.println("线程开始，name = " + name + ", i = " + i);
            }
        }
    }
    public static void main(String[] args) {
        Thread t1 = new MyThread("Thread A");
        Thread t2 = new MyThread("Thread B");
        t1.start();
        t2.start();
}
}
