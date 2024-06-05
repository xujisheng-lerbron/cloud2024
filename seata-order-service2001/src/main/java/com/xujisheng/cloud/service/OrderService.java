package com.xujisheng.cloud.service;

import com.xujisheng.cloud.entities.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}


