package com.demo.lrl.utils;

import com.demo.lrl.annotation.FruitColor;
import com.demo.lrl.annotation.FruitName;
import com.demo.lrl.annotation.FruitProvider;

import java.lang.reflect.Field;

/**
 * @author liuruilin
 */
public class FruitUtils {
    public static void getFruit(Class<?> clz){
        String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvider="供应商信息：";

        for (Field field : clz.getDeclaredFields()) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName = strFruitName + fruitName.value();
                System.out.println("strFruitName = " + strFruitName);
            }else if(field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
                System.out.println("strFruitColor = " + strFruitColor);
            }else if(field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider = strFruitProvider + " 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                System.out.println("strFruitProvider = " + strFruitProvider);
            }

        }
    }
}
