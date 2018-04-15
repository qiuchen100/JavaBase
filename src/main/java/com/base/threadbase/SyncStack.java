package com.base.threadbase;

class MyStack {
    private int idx = 0;
    private char[] chars = new char[100];

    public void push(char c) {
        chars[idx] = c;
        idx++;
    }

    public char pop() {
        idx--;
        return chars[idx];
    }

    @Override
    public String toString() {
        return new String(chars);
    }
}
public class SyncStack {
    static class MyThread extends Thread {
        private char name;
        private MyStack stack;
        public MyThread(char name, MyStack stack) {
            this.name = name;
            this.stack = stack;
        }
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 50; i++) {
                stack.push(name);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyStack stack = new MyStack();
        MyThread t1 = new MyThread('A', stack);
        MyThread t2 = new MyThread('B', stack);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(stack.toString());
    }
}
