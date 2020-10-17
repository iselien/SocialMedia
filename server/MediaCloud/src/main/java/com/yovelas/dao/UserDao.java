package com.yovelas.dao;

import com.yovelas.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private SqlSession sqlSession;

    public User getUserByName(String username) {
        return sqlSession.selectOne("com.yovelas.mapper.UserMapper.getUserByName", username);
    }

    public User getUserByUP(User user) {
        return sqlSession.selectOne("com.yovelas.mapper.UserMapper.getUserByUP", user);
    }

    public int insertUser(User user){
        return sqlSession.insert("com.yovelas.mapper.UserMapper.insertUser",user);
    }
}


//    public int InsertUser(User user) {
//        System.out.println(user);
//        return sqlSession.insert("com.yovelas.music.mapper.UserMapper.InsertUser", user);
//    }
//
//    public int setpawd(User user) {
//        return sqlSession.update("com.yovelas.music.mapper.UserMapper.setpawd", user);
//    }
