package com.base.threadbase;

public class JoinTest {
    static class MyThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.start();
        thread.join();
        System.out.println("Dead!");
    }
}
