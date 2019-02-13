package com.base.threadbase;

import java.util.Random;
import java.util.Stack;

public class StoreHouse {
    Stack<String> products = new Stack<String>();
    Random random = new Random();

    public void produce() {
        synchronized (products) {
            for (int i = 0; i < 3; i++) {
                String item = "商品" + String.valueOf(random.nextInt(25));
                products.push(item);
                System.out.println("已上架商品：" + item);
            }
            products.notify();
        }

    }

    public void consume() throws InterruptedException {
        synchronized (products) {
            while (products.empty()) {
                System.out.println("商品栏已空！ ");
                products.wait();
                Thread.sleep(1000);
            }
            String item = products.pop();
            System.out.println("已消费商品：" + item);
        }
    }

    static class Producer implements Runnable{
        private StoreHouse store;

        public Producer(StoreHouse store) {
            this.store = store;
        }

        @Override
        public void run() {
            while (true) {
                store.produce();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        private StoreHouse store;

        public Consumer(StoreHouse store) {
            this.store = store;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    store.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StoreHouse storeHouse = new StoreHouse();
        Thread producerThread = new Thread(new Producer(storeHouse));
        Thread consumerThread = new Thread(new Consumer(storeHouse));
        producerThread.setDaemon(true);
        producerThread.start();
        consumerThread.start();
        consumerThread.join();
        System.out.println("程序结束！");
    }
}
