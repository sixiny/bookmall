package com.ysx.book.dao;

import com.ysx.book.pojo.OrderBean;
import com.ysx.book.pojo.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/21:27
 * @Description:
 */
public interface OrderDao {
    void addOrderBean(OrderBean orderBean);
    // 获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);
    // 获取订单总量
    Integer getOrderTotalBookCount(OrderBean orderBean);
}
