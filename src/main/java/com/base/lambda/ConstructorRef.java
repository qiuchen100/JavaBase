package com.base.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 构造函数引用
 *
 * @author 邱晨
 * @version 1.0
 */
class Employee {
    String name;
    public Employee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
public class ConstructorRef {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        String[] arr_names = {"Kate", "Tom", "Jimmy", "Anne"};
        List<String> names = Arrays.asList(arr_names);
        Stream<Employee> stream = names.stream().map(Employee::new);
        stream.forEach(System.out::println);
    }
}
