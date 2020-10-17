package com.yovelas.service;

import com.yovelas.dao.UserDao;
import com.yovelas.dao.UserLogDao;
import com.yovelas.entity.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogService {

    @Autowired
    UserLogDao userLogDao;

    public int insertUserLog(UserLog userLog) {
        return userLogDao.insertUserLog(userLog);
    }

}
