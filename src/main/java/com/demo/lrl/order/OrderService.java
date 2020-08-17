package com.demo.lrl.order;

/**
 * @author liuruilin
 */

public interface OrderService {
    /**执行*/
    void execute();
    /**查询*/
    void query();

    /**
     * 判断哪个类
     * @return
     */
    OrderEnum getOrderCode();
}
