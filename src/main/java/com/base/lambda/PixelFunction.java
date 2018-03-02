package com.base.lambda;

/**
 * 自定义函数引用
 *
 * @author 邱晨
 * @version 1.0
 */
@FunctionalInterface
public interface PixelFunction {
    String apply(int x, int y);
}
