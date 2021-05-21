package com.atguigu.constants;

public class Constants {
    /*
       订单状态
     */
    /**
     * 待支付
     */
    public static final Integer ORDER_STATUS_PENDING_PAYMENT = 0;

    /**
     * 待发货
     */
    public static final Integer ORDER_STATUS_PENDING_SHIP = 1;

    /**
     * 待收货
     */
    public static final Integer ORDER_STATUS_PENDING_ARRIVE = 2;

    /**
     * 已签收
     */
    public static final Integer ORDER_STATUS_SIGNED = 3;

}
