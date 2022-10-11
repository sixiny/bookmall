package com.ysx.book.service;

import com.ysx.book.pojo.OrderBean;
import com.ysx.book.pojo.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/21:37
 * @Description:
 */
public interface OrderService {
    // 结账功能
    // 1、删除购物车信息  cart中 list一次遍历删除
    // 2、添加订单信息
    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);

}
