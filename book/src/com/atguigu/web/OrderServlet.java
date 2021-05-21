package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);

//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order>  allOrder = orderService.showAllOrders();
        //回传数据
        req.setAttribute("allOrder", allOrder);
        //返回页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    protected void sendOrder (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        List<Order> allOrder = orderService.showAllOrders();
        req.setAttribute("allOrder", allOrder);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }


    protected void showOrderDetail (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req,resp);
    }

    protected void showMyOrder (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null ){
            List<Order> myOrders = orderService.showMyOrder(user.getId());
            req.setAttribute("myOrders",myOrders);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/pages/user/login.jsp");
        }
    }

    protected void receiveOrder (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiveOrder(orderId);
        List<Order> allOrder = orderService.showAllOrders();
        req.setAttribute("allOrder", allOrder);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    protected void payOrder (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        User user = (User) req.getSession().getAttribute("user");
        orderService.payOrder(orderId);
        List<Order> myOrders = orderService.showMyOrder(user.getId());
        req.setAttribute("myOrders", myOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }

    protected void signOrder (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        User user = (User) req.getSession().getAttribute("user");
        orderService.signOrder(orderId);
        List<Order> myOrders = orderService.showMyOrder(user.getId());
        req.setAttribute("myOrders", myOrders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }



}
