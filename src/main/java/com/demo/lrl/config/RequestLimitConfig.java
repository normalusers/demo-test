package com.demo.lrl.config;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author liuruilin
 */
@Data
public class RequestLimitConfig implements Serializable {
    private static final long serialVersionUID = 1101875328323558092L;
    /** 最大请求次数*/
    private long limit;
    /**时间*/
    private long time;
    /**时间单位*/
    private TimeUnit timeUnit;
}
