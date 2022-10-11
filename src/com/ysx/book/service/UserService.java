package com.ysx.book.service;

import com.ysx.book.pojo.User;

import java.util.List;

public interface UserService {
    User login(String uname , String pwd );

    void regist(User user);

    //获取所有用户姓名
    List<String> getNames();

    User getUser(String uName);
}
