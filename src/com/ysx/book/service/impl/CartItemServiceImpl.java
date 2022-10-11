package com.ysx.book.service.impl;

import com.ysx.book.dao.CartItemDao;
import com.ysx.book.pojo.Book;
import com.ysx.book.pojo.Cart;
import com.ysx.book.pojo.CartItem;
import com.ysx.book.pojo.User;
import com.ysx.book.service.BookService;
import com.ysx.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/15:12
 * @Description:  购物车项
 */
public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao;
    private BookService bookService;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDao.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDao.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //判断当前的购物车时候有这本书的cartitem  有->update 没-> add
        //1、如果当前用户的购物车已有这个图书  那个购物车中书的数量+1
        //2、没有 就新增一个CartItem 数量是1
        if(cart!=null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap==null){
                cartItemMap = new HashMap<>();
            }
            if(cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItem1 = cartItemMap.get(cartItem.getBook().getId());
                cartItem1.setBuyCount(cartItem1.getBuyCount() + 1);
                updateCartItem(cartItem1);
            }else{
                addCartItem(cartItem);
            }
        }else{ //连购物车都没有  就直接加
            addCartItem(cartItem);
        }
    }

    @Override
    public Cart getCart(User user) {
        //调用自己的 获取全部信息的cartItem
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for(CartItem temp:cartItemList){
            cartItemMap.put(temp.getBook().getId(), temp);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDao.getCartItemList(user);
        //循环找出book
        for(CartItem item:cartItemList){
            Book book = bookService.getBook(item.getBook().getId());
            item.setBook(book);
            item.getXj(); //vue属于前后端分离 没法去通过get获取小计 要给他计算好
        }
        return cartItemList;
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        cartItemDao.delCartItem(cartItem);
    }

    @Override
    public CartItem getCartItem(Integer id) {
        return cartItemDao.getCartItem(id);
    }
}
