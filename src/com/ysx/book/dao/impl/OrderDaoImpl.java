package com.ysx.book.dao.impl;

import com.ysx.book.dao.OrderDao;
import com.ysx.book.pojo.OrderBean;
import com.ysx.book.pojo.User;
import com.ysx.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/21:30
 * @Description:
 */
public class OrderDaoImpl extends BaseDAO<OrderBean> implements OrderDao {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int i = executeUpdate("insert into t_order values(0, ? , ?, ?, ?, ?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(i);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return executeQuery("select * from t_order where orderUser=?", user.getId());
    }

    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
//        String sql = "SELECT SUM(t3.buyCount) AS totalBookCount , t3.orderBean FROM " +
//                "(" +
//                "SELECT t1.id , t2.buyCount , t2.orderBean FROM t_order t1 INNER JOIN t_order_item t2 " +
//                "ON t1.id = t2.orderBean WHERE t1.orderUser = ? " +
//                ") t3 WHERE t3.orderBean = ? GROUP BY t3.orderBean" ;
        String sql = "SELECT SUM(buyCount) FROM t_order_item WHERE orderBean=?";
        return ((BigDecimal)executeComplexQuery(sql,orderBean.getId())[0]).intValue();
    }
}
