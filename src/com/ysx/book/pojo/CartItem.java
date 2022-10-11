package com.ysx.book.pojo;

import java.math.BigDecimal;

//我们应该还需要设计一个Cart类，代表购物车这个实体
public class CartItem {
    private Integer id ;
    private Book book ;
    private Integer buyCount ;
    private User userBean ;
    private Double xj;

    public CartItem(){}

    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Book book, Integer buyCount, User uesrBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = uesrBean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUesrBean() {
        return userBean;
    }

    public void setUesrBean(User uesrBean) {
        this.userBean = uesrBean;
    }

    public Double getXj() {
        BigDecimal bigDecimalPrice = new BigDecimal(getBook().getPrice() + "");
        BigDecimal bigDecimalBuyCount = new BigDecimal("" + buyCount);
        BigDecimal bDxj = bigDecimalPrice.multiply(bigDecimalBuyCount);
        xj = bDxj.doubleValue();
        return xj;
    }
}
