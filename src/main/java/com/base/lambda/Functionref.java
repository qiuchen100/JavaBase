package com.base.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 方法引用
 * @author 邱晨
 * @version 1.0
 */
public class Functionref {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("xxx");
        list.add("ooo");
        list.add(null);
        list.add("ggg");
        System.out.println(list.size());

        //传统方法
//        list.removeIf(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s == null;
//            }
//        });
        //lambda方法
        list.removeIf(e -> e == null);
        System.out.println(list.size());

        list.add(null);
        System.out.println(list.size());
        list.removeIf(Objects::isNull); //方法引用 类::静态方法
        System.out.println(list.size());

        //list.forEach(x -> System.out.println(x));
        list.forEach(System.out::println); //对象::实例方法

    }
}
