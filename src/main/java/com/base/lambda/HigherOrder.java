package com.base.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 高阶函数示例
 * @author 邱晨
 * @version 1.0
 */
public class HigherOrder {

    //处理和返回函数的函数被称为高阶函数
    public static Comparator<String> compareInDirection(int direction) {
        return (x, y) -> direction * x.compareTo(y);
    }
    public static void main(String[] args) {
        String[] friends = {"Kate", "Amy", "Anne", "Berry"};
        Arrays.sort(friends, compareInDirection(1));
        for (String friend : friends) {
            System.out.println(friend);
        }

        Arrays.sort(friends, compareInDirection(-1));
        for (String friend : friends) {
            System.out.println(friend);
        }
    }
}
