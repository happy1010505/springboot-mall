package com.harry.springbootmall.service.impl;

import com.harry.springbootmall.dao.OrderDao;
import com.harry.springbootmall.dao.ProductDao;
import com.harry.springbootmall.dao.UserDao;
import com.harry.springbootmall.dto.BuyItem;
import com.harry.springbootmall.dto.CreateOrderRequest;
import com.harry.springbootmall.model.Order;
import com.harry.springbootmall.model.OrderItem;
import com.harry.springbootmall.model.Product;
import com.harry.springbootmall.model.User;
import com.harry.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createdOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);

        if (user == null) {
            logger.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            if (product == null) {
                logger.warn("商品{} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                logger.warn("商品{}庫存數量不足,無法購買. 剩餘庫存{} 欲購買數量{}" ,buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            }
            productDao.updateStock(product.getProductId(),product.getStock()- buyItem.getQuantity());

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
