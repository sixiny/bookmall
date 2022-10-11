package com.ysx.book.service;

import com.ysx.book.pojo.Book;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/01/20:35
 * @Description:
 */
public interface BookService {
    List<Book> getBookList();
    //根据id查询书
    Book getBook(Integer id);

    //提交订单修改库存书籍数量
    void changeBookCount(Book book, int buyCount);
}
