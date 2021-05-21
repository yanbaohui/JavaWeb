package com.atguigu.test;

import com.atguigu.constants.Constants;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void showAllOrders() {

        OrderService orderService = new OrderServiceImpl();
        for (Order order : orderService.showAllOrders()) {
            System.out.println(order.getUserId());
        }
    }


    @Test
    public void sendOrder() {
        OrderService orderService = new OrderServiceImpl();
        List<Order> list = orderService.showAllOrders();
        for (Order order : list) {
            System.out.println(order.getStatus());
            orderService.sendOrder(order.getOrderId());
            System.out.println(order.getStatus());
        }
    }

    @Test
    public void showOrderDetail() {
        OrderService orderService = new OrderServiceImpl();
        for (Order order : orderService.showAllOrders()) {
            System.out.println(orderService.showOrderDetail(order.getOrderId()));
        }

    }
}