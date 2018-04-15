package com.base.threadbase;

class PrintClass implements Runnable{
    public static int i = 0;
    static Object obj = new Object();
    public void addI(){
        synchronized (obj) {
            i = i + 1;
        }

        //System.out.println(PrintClass.i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 15000; j++) {
            addI();
        }
    }
}
public class SyncTest {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new PrintClass());
        Thread b = new Thread(new PrintClass());
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(PrintClass.i);
    }
}
