package com.base.concurrent.demo;

/**
 * 定义线程--小D课堂
 * @author 邱晨
 * @version 1.0
 */
public class MyThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });

        //会打印出main，因为run只是被当作普通方法调用，仍然运行在主线程
        thread.run();


    }
}
