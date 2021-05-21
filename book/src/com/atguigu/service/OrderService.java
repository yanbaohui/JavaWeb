package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);

    List<Order> showAllOrders();

    void sendOrder(String id);

    List<OrderItem> showOrderDetail(String orderId);

    List<Order> showMyOrder(Integer id);

    void receiveOrder(String orderId);

    void payOrder(String orderId);

    void signOrder(String orderId);
}
