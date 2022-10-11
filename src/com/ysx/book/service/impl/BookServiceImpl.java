package com.ysx.book.service.impl;

import com.ysx.book.dao.BookDao;
import com.ysx.book.pojo.Book;
import com.ysx.book.service.BookService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/01/20:35
 * @Description:
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDao.getBook(id);
    }

    @Override
    public void changeBookCount(Book book, int buyCount) {
        bookDao.changeBookCount(book, buyCount);
    }
}
