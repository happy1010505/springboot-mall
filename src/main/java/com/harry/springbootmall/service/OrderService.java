package com.harry.springbootmall.service;

import com.harry.springbootmall.dto.CreateOrderRequest;

public interface OrderService {
    Integer createdOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
