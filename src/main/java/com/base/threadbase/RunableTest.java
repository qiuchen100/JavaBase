package com.base.threadbase;

/**
 * Runable实现多线程
 * @author 邱晨
 * @version 1.0
 */
public class RunableTest {
    static class MyRun implements Runnable {
        private String name;
        public MyRun(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println("线程开始，name = " + name + ", i = " + i);
            }
        }
    }
    public static void main(String[] args) {
        MyRun r1 = new MyRun("Runnable A");
        MyRun r2 = new MyRun("Runnable B");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
