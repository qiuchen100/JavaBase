package com.base.threadbase;

class ThreadA extends Thread{
    Object obj;
    public ThreadA(Object obj) {
        this.obj = obj;
    }
    public void run() {
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait");
        }
    }
}

class ThreadB extends Thread {
    Object obj;
    public ThreadB(Object obj) {
        this.obj = obj;
    }
    public void run(){
        synchronized (obj) {
            obj.notify();
            System.out.println("notify");
        }
    }
}
public class NotifyWait {
    public static void main(String[] args) {
        Object obj = new Object();
        ThreadA threadA = new ThreadA(obj);
        ThreadB threadB = new ThreadB(obj);
        threadA.start();
        threadB.start();
    }
}
