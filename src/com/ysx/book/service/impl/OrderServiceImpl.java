package com.ysx.book.service.impl;

import com.ysx.book.dao.CartItemDao;
import com.ysx.book.dao.OrderDao;
import com.ysx.book.dao.OrderItemDao;
import com.ysx.book.pojo.*;
import com.ysx.book.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/21:41
 * @Description:
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private CartItemDao cartItemDao;


    @Override
    public void addOrderBean(OrderBean orderBean) {
        //1) 订单表添加一条记录
        //2) 订单详情表添加7条记录 购物车的记录   cart -> cartItemMap
        //3) 购物车项表中需要删除对应的7条记录
        //第一步：
        orderDao.addOrderBean(orderBean);
        //第二步:
        //orderBean中的orderItemList是空的，此处我们应该根据用户的购物车中的购物车项去转换成一个一个的订单项
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for(CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDao.addOrderItem(orderItem);
        }

        //第三步:
        for(CartItem temp:cartItemMap.values()){
            cartItemDao.delCartItem(temp);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = orderDao.getOrderList(user);

        for (OrderBean orderBean: orderBeanList) {
            Integer totalBookCount = orderDao.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
        }

        return orderBeanList ;
    }
}
