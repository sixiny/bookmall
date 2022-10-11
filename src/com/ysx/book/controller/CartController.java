package com.ysx.book.controller;

import com.ysx.book.pojo.Book;
import com.ysx.book.pojo.Cart;
import com.ysx.book.pojo.CartItem;
import com.ysx.book.pojo.User;
import com.ysx.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/14:58
 * @Description:购物车控制器
 */
public class CartController {
    private CartItemService cartItemService;

    // 加载当前用户购物车信息
    public String index(HttpSession session){
        updateCart(session);
        return  "cart/cart";
    }

    // 更新购物车信息
    public void updateCart(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("corrUser", user);
    }

    public String addCart(Integer bookId, HttpSession session){
        // 将指定的图书添加到当前用户的购物车上
        User user = (User)session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());
        return "redirect:cart.do";  //默认index方法  index应该去更新购物车信息
    }
    // 删除某个购物车项
    public String delCartItem(Integer cartId){
        cartItemService.delCartItem(new CartItem(cartId));
        return "redirect:cart.do";
    }

    //清空购物车
    public String delCart(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Map<Integer, CartItem> cartItemMap = user.getCart().getCartItemMap();
        Collection<CartItem> values = cartItemMap.values();
        for(CartItem temp:values){
            cartItemService.delCartItem(temp);
        }
        return "redirect:cart.do";
    }

    /**
     *
     * @param cartId
     * @return 增删操作 其实可以直接把数量传进来 这样就可以省下来代码量了
     */
    // 购物车界面添加减少书本数量
    public String redBookCount(Integer cartId, HttpSession session){
        CartItem cartItem = cartItemService.getCartItem(cartId);
        if(cartItem.getBuyCount() <= 1){
            cartItemService.delCartItem(cartItem);
        }else{
            cartItem.setBuyCount(cartItem.getBuyCount() -1);
            cartItemService.updateCartItem(cartItem);
        }
        updateCart(session);
        return "";
    }

    //增加书本量
    public String addBookCount(Integer cartId, HttpSession session){
        CartItem cartItem = cartItemService.getCartItem(cartId);
        cartItem.setBuyCount(cartItem.getBuyCount() + 1);
        cartItemService.updateCartItem(cartItem);
        updateCart(session);
        return "";
    }

    public String cartInfo(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        //调用cart中的三个属性的get 因为vue前后分离了 否则这三个为null
        //否者转为json为null的会被忽略
        cart.getTotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:" + cartJsonStr;
    }

}
