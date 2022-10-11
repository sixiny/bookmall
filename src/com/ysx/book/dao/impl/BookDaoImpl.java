package com.ysx.book.dao.impl;

import com.ysx.book.dao.BookDao;
import com.ysx.book.pojo.Book;
import com.ysx.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/01/20:32
 * @Description:
 */
public class BookDaoImpl  extends BaseDAO<Book> implements BookDao {
    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book where bookStates=0");
    }

    @Override
    public Book getBook(Integer id) {
        return load("select * from t_book where id=?", id);
    }

    @Override
    public void changeBookCount(Book book, int saleOut) {
        executeUpdate("update t_book set saleCount=?,bookCount=? where id=?", saleOut + book.getSaleCount(), book.getBookCount() - saleOut, book.getId());
    }



}
