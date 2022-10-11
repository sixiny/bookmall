package com.ysx.book.pojo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/02/15:15
 * @Description:
 */
public class Cart {
    private Map<Integer, CartItem> cartItemMap; // 购物车中购物车项集合 id是bookid 判断是否含有书
    private Double totalMoney; //购物车的总金额
    private Integer totalCount; //购物车中购物项的shuliang
    private Integer totalBookCount; //购物车中书本总数量

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Integer getTotalBookCount() {
        totalBookCount = 0 ;
        if(cartItemMap!=null && cartItemMap.size()>0){
            for (CartItem cartItem : cartItemMap.values()){
                totalBookCount = totalBookCount + cartItem.getBuyCount() ;
            }
        }
        return totalBookCount;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        BigDecimal bigDecimal = new BigDecimal(totalMoney + "");
        if(cartItemMap!=null && cartItemMap.size()>0){
            Collection<CartItem> values = cartItemMap.values();
            for(CartItem item:values){
                BigDecimal multiply = new BigDecimal(item.getBuyCount() + "").multiply(new BigDecimal(item.getBook().getPrice() + ""));
                bigDecimal = bigDecimal.add(multiply);
            }
        }
        totalMoney = bigDecimal.doubleValue();
        return totalMoney;
    }


    public Integer getTotalCount() {
        totalCount = 0 ;
        if(cartItemMap!=null && cartItemMap.size()>0){
            totalCount = cartItemMap.size() ;
        }
        return totalCount;
    }
}
