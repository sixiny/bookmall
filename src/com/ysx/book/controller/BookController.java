package com.ysx.book.controller;

import com.ysx.book.pojo.Book;
import com.ysx.book.service.BookService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/01/21:06
 * @Description:
 */
public class BookController {
    private BookService bookService;

    public String index(HttpSession session){
        return "index";
    }

    public String getBookList(){
        List<Book> bookList = bookService.getBookList();
//        session.setAttribute("bookList", bookList);
        Gson gson = new Gson();
        String strbookList = gson.toJson(bookList);
        return "json:" + strbookList;
    }
}
