package io.github.yovelas.dao;

import io.github.yovelas.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private SqlSession sqlSession;

    public User getUserByName(String username) {
        return sqlSession.selectOne("io.github.yovelas.mapper.UserMapper.getUserByName", username);
    }

    public User getUserById(int id) {
        return sqlSession.selectOne("io.github.yovelas.mapper.UserMapper.getUserById", id);
    }

    public User getUserByUP(User user) {
        return sqlSession.selectOne("io.github.yovelas.mapper.UserMapper.getUserByUP", user);
    }

    public int insertUser(User user){
        return sqlSession.insert("io.github.yovelas.mapper.UserMapper.insertUser",user);
    }
}


