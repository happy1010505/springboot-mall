package com.harry.springbootmall.service.impl;

import com.harry.springbootmall.dao.OrderDao;
import com.harry.springbootmall.dao.ProductDao;
import com.harry.springbootmall.dto.BuyItem;
import com.harry.springbootmall.dto.CreateOrderRequest;
import com.harry.springbootmall.model.Order;
import com.harry.springbootmall.model.OrderItem;
import com.harry.springbootmall.model.Product;
import com.harry.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createdOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }


        Integer orderId = orderDao.createdOrder(userId,totalAmount);
        orderDao.createOrderItems(orderId,orderItemList);


        return orderId;

    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemById(orderId);
        order.setOrderItemList(orderItemList);
        return order;

    }
}
