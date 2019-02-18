package com.base.concurrent.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定义线程--小D课堂
 * @author 邱晨
 * @version 1.0
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });

    }
}
