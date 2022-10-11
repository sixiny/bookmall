package com.ysx.book.dao.impl;

import com.ysx.book.dao.CartItemDao;
import com.ysx.book.pojo.CartItem;
import com.ysx.book.pojo.User;
import com.ysx.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/15:04
 * @Description:
 */
public class CartItemDaoImpl extends BaseDAO<CartItem> implements CartItemDao {
    @Override
    public void addCartItem(CartItem cartItem) {
        executeUpdate("insert into t_cart_item values(0, ?, ?, ?)", cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getUesrBean().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        executeUpdate("update t_cart_item set buyCount=? where id=?", cartItem.getBuyCount(), cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return executeQuery("select * from t_cart_item where userBean = ? " , user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        executeUpdate("delete from t_cart_item where id = ?" , cartItem.getId());
    }

    @Override
    public CartItem getCartItem(Integer id) {
        return load("select * from t_cart_item where id = ?", id);
    }
}
