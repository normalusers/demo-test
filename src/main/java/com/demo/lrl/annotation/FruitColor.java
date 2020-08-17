package com.demo.lrl.annotation;

import java.lang.annotation.*;

/**
 * @author liuruilin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitColor {
    /**
     * 颜色枚举
     */
    public enum Color{BLUE,RED,WHITE};

    /**
     * 颜色属性
     * @return
     */
    Color fruitColor() default Color.RED;
}
