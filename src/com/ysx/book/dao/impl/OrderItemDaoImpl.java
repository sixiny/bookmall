package com.ysx.book.dao.impl;

import com.ysx.book.dao.OrderItemDao;
import com.ysx.book.pojo.OrderItem;
import com.ysx.myssm.basedao.BaseDAO;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/21:33
 * @Description:
 */
public class OrderItemDaoImpl extends BaseDAO<OrderItem> implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        executeUpdate("insert into t_order_item values(0, ?, ?, ?)", orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
