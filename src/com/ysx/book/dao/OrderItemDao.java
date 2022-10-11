package com.ysx.book.dao;

import com.ysx.book.pojo.OrderItem;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/21:27
 * @Description:
 */
public interface OrderItemDao {
    // 添加订单项
    void addOrderItem(OrderItem orderItem);
}
