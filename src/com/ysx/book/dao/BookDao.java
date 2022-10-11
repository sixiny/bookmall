package com.ysx.book.dao;

import com.ysx.book.pojo.Book;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/01/20:31
 * @Description:
 */
public interface BookDao {
    List<Book> getBookList();

    Book getBook(Integer id);

    void changeBookCount(Book book, int saleOut);

}
