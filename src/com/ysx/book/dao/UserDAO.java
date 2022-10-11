package com.ysx.book.dao;

import com.ysx.book.pojo.User;

import java.util.List;

public interface UserDAO {
    User getUser(String uname , String pwd );

    //添加用户
    void addUser(User user);

    //获取所有用户名 用来注册时验证是否已经存在
    List<User> getUnames();

    User getUser(String uName);
}
