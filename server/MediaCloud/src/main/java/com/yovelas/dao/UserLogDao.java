package com.yovelas.dao;

import com.yovelas.entity.User;
import com.yovelas.entity.UserLog;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLogDao {

    @Autowired
    private SqlSession sqlSession;

    public int insertUserLog(UserLog userLog) {
        return sqlSession.insert("com.yovelas.mapper.UserLogMapper.insertUserLog",userLog);
    }

}


