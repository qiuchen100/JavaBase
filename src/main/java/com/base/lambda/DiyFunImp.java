package com.base.lambda;

/**
 * 自定义函数引用
 *
 * @author 邱晨
 * @version 1.0
 */
public class DiyFunImp {
    public static String getColor(int x, int y, PixelFunction pixel) {
        return pixel.apply(x, y);
    }
    public static void main(String[] args) {
        System.out.println(getColor(50, 60, (x, y) -> x < 50 ? "BLUE" : x >= 50 && y < 50 ? "RED" : "GREEN"));
    }
}
