package com.demo.lrl.common;

import com.demo.lrl.annotation.FruitColor;
import com.demo.lrl.annotation.FruitName;
import com.demo.lrl.annotation.FruitProvider;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liuruilin
 */
@Component
@Data
public class TestAA {

    @Value("${server.port}")
    protected String port;
    @FruitName("Apple")
    private String appleName;
    @FruitColor(fruitColor = FruitColor.Color.BLUE)
    private String appColor;
    @FruitProvider(id = 1, name = "陕西红富士集团", address = "北京中坤大厦")
    private String appProvider;
}
