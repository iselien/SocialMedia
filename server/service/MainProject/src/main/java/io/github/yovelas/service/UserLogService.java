package io.github.yovelas.service;

import io.github.yovelas.dao.UserLogDao;
import io.github.yovelas.entity.UserLog;
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
