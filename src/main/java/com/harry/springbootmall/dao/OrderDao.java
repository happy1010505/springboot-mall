package com.harry.springbootmall.dao;

import com.harry.springbootmall.dto.CreateOrderRequest;
import com.harry.springbootmall.model.Order;
import com.harry.springbootmall.model.OrderItem;
import com.harry.springbootmall.model.User;

import java.util.List;

public interface OrderDao {
    Integer createdOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemById(Integer orderId);
}
