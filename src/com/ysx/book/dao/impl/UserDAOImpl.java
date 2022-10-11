package com.ysx.book.dao.impl;

import com.ysx.book.dao.UserDAO;
import com.ysx.book.pojo.User;
import com.ysx.myssm.basedao.BaseDAO;

import java.util.List;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return load("select * from t_user where uname like ? and pwd like ? " , uname , pwd );
    }

    @Override
    public void addUser(User user) {
        executeUpdate("insert into `t_user` values(0, ?, ?, ?, 0)", user.getUname(), user.getPwd(), user.getEmail());
    }

    @Override
    public List<User> getUnames() {
        return executeQuery("select uname from t_user");
    }

    @Override
    public User getUser(String uName) {
        return load("select *  from t_user where uname=?", uName);
    }
}
