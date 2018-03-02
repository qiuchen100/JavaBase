package com.base.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 使用lambda表达式来实现Arrays.sort
 * @author 邱晨
 * @version 1.0
 */
public class ArraysSort {

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String[] words = {"page", "eleven", "cat", "mouse", "tv"};

        //传统实现方式
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        for (String word : words) {
            System.out.println(word);
        }
        System.out.println("----------以上是传统的实现方法，现在用lambda表达式来实现-----------");

        //lambda表达式实现方式
        Arrays.sort(words, (x, y) -> y.length() - x.length());
        for (String word : words) {
            System.out.println(word);
        }
    }
}
