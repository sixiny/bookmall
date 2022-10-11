package com.ysx.book.dao;

import com.ysx.book.pojo.CartItem;
import com.ysx.book.pojo.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/15:02
 * @Description:
 */
public interface CartItemDao {
    //新增购物车项
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);
    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
    //删除指定购物车项
    void delCartItem(CartItem cartItem);
    //获取指定购物车项
    CartItem getCartItem(Integer id);
}
