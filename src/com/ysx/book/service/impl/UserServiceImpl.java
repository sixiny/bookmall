package com.ysx.book.service.impl;

import com.ysx.book.dao.UserDAO;
import com.ysx.book.pojo.User;
import com.ysx.book.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO ;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname, pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        List<User> users = userDAO.getUnames();
        for(User temp:users){
            names.add(temp.getUname());
        }
        return names;
    }

    @Override
    public User getUser(String uName) {
        return userDAO.getUser(uName);
    }


}
