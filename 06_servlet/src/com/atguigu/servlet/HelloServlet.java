package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {

        System.out.println("servlet，被访问了");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestMethod = httpServletRequest.getMethod();

        if("post".equals(requestMethod)){
            doGet();
        }else if ("get".equals(requestMethod)){
            doPost();
        }
    }


    public void doGet(){
        System.out.println("post请求");
    }

    public void doPost(){
        System.out.println("get请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
