package com.ysx.book.test;

import com.ysx.book.dao.BookDao;
import com.ysx.book.dao.OrderDao;
import com.ysx.book.dao.UserDAO;
import com.ysx.book.dao.impl.BookDaoImpl;
import com.ysx.book.dao.impl.OrderDaoImpl;
import com.ysx.book.dao.impl.UserDAOImpl;
import com.ysx.book.pojo.Book;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/01/21:00
 * @Description:
 */
public class BookTest {
    private BookDao bookDao = new BookDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private UserDAO userDAO = new UserDAOImpl();

    @Test
    public void test(){
        List<Book> bookList = bookDao.getBookList();
        Stream<Book> stream = bookList.stream();
//        stream.forEach(System.out::println);
        stream.sorted(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int)(o1.getPrice() - o2.getPrice());
            }
        }).forEach(System.out::println);
        System.out.println("*******Stream表达式**********");
        Stream<Book> stream2 = bookList.stream();
//        stream.forEach(System.out::println);
        stream2.sorted((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice())).forEach(System.out::println);
        System.out.println("*****************");
        Stream<Book> stream1 = bookList.stream();
        stream1.forEach(System.out::println);

        System.out.println("******************");
    }

    @Test
    public void test1(){
//        User user = new User("ysh", "ysh", "ysh@qq.com", 1);
//        userDAO.addUser(user);
    }



}
