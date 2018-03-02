package com.base.threads;

class DaemonThread extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("OK");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class DaemonTest {
    public static void main(String[] args) {
        DaemonThread thread = new DaemonThread();
        thread.setDaemon(true);  //Start之前setDaemon(true)
        thread.start();
        System.out.println("Dead!");//进程中只有守护线程时，便可结束运行
        System.out.println("Is Daemon: " + thread.isDaemon());
        System.out.println("Priority: " + thread.getPriority());
    }
}
