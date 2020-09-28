package io.github.yovelas.dao;

import io.github.yovelas.entity.UserLog;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLogDao {

    @Autowired
    private SqlSession sqlSession;

    public int insertUserLog(UserLog userLog) {
        return sqlSession.insert("io.github.yovelas.mapper.UserLogMapper.insertUserLog",userLog);
    }

}


