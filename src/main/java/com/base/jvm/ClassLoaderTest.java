package com.base.jvm;

import java.lang.reflect.Field;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(ClassLoader.getSystemClassLoader().toString());
        String s = "tt";
        Class<?> c1 = s.getClass();
        System.out.println(c1.getName());
        Class<?> c2 = Class.forName("java.lang.String");
        System.out.println(c2.getName());
        for (Field f : c2.getFields()) {
            System.out.println(f.toGenericString());
        }
        System.out.println(Thread.currentThread().getContextClassLoader().toString());
    }
}
