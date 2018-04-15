package com.base.threadbase;

public class SyncAdd {
    static class MyThread extends Thread {
        static int num;
        static Object obj = new Object();
        @Override
        public void run() {
            super.run();
            synchronized (obj) {
                for (int i = 0; i < 100000; i++) {
                    num++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(MyThread.num);
    }
}
