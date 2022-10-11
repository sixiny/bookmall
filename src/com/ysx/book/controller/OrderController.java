package com.ysx.book.controller;

import com.ysx.book.pojo.CartItem;
import com.ysx.book.pojo.OrderBean;
import com.ysx.book.pojo.User;
import com.ysx.book.service.BookService;
import com.ysx.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/21:58
 * @Description:
 */
public class OrderController {
    private OrderService orderService;
    private BookService bookService;
    //结账
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();
        Date now = new Date();
        int year = now.getYear();
        int month = now.getMonth();
        int day = now.getDay();
        int hours = now.getHours();
        int minutes = now.getMinutes();
        int seconds = now.getSeconds();
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+year+month+day+hours+minutes+seconds);
        orderBean.setOrderDate(now);
        User currUser = (User) session.getAttribute("currUser");
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        Collection<CartItem> values = cartItemMap.values();
        for(CartItem temp:values){
            bookService.changeBookCount(temp.getBook(), temp.getBuyCount());
        }

        orderBean.setOrderUser(currUser);
        orderBean.setOrderMoney(currUser.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);
        session.setAttribute("currOrder", orderBean);
        orderService.addOrderBean(orderBean);
        return "cart/checkout";
    }

    // 查看订单列表
    public String getOrderList(HttpSession session){
        User user = (User) session.getAttribute("currUser"); //通过user id 查询订单信息
        List<OrderBean> orderList = orderService.getOrderList(user);
        user.setOrderList(orderList);
        return "order/order";
    }


}
