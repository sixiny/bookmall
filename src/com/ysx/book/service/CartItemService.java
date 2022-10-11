package com.ysx.book.service;

import com.ysx.book.pojo.Cart;
import com.ysx.book.pojo.CartItem;
import com.ysx.book.pojo.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/15:12
 * @Description:
 */
public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //加载特定用户的购物车信息
    Cart getCart(User user);

    //获取指定用户的所有购物车项列表  这个封装的时候 需要将详细的book信息封装进去
    // 不然后续通过book查询信息会出问题
    List<CartItem> getCartItemList(User user);

    //删除指定Cartitem
    void delCartItem(CartItem cartItem);

    //获取指定CartItem
    CartItem getCartItem(Integer id);

}
