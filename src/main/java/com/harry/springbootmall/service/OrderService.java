package com.harry.springbootmall.service;

import com.harry.springbootmall.dto.CreateOrderRequest;
import com.harry.springbootmall.model.Order;
import com.harry.springbootmall.model.User;

public interface OrderService {
    Integer createdOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);

}
