package com.yovelas.service;

import com.yovelas.dao.UserDao;
import com.yovelas.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    public User getUserByUP(User user) {
        return userDao.getUserByUP(user);
    }

    public int insertUser(User user){
        return userDao.insertUser(user);
    }
}
