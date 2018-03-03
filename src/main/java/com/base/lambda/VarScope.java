package com.base.lambda;

/**
 * lambda表达式和变量作用域
 * @author 邱晨
 * @version 1.0
 */
public class VarScope {
    public static void repeatMessage(String text, int count) {
        //描述带有自由变量值的代码块就是闭包。在Java中，lambda表达式就是闭包。
        Runnable r = () -> {
            for (int i = 0; i < count; i++) {  //对该lambda表达式来说，text和count都是自由变量
                //count--; !lmabda表达式只能引用那些值不会改变的变量
                System.out.println(text);
            }
        };
        new Thread(r).start();
    }
    public static void main(String[] args) {
        repeatMessage("xxx", 10);
    }
}
