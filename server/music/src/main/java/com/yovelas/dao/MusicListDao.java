package com.yovelas.dao;

import java.util.List;
import java.util.Map;

import com.yovelas.entity.Music;
import com.yovelas.entity.MusicList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicListDao {

    @Autowired
    private SqlSession sqlSession;

    public List<MusicList> getUserMusicList(Map map) {
        return sqlSession.selectList("com.yovelas.mapper.MusicListMapper.getUserMusicList",map);
    }




    public List<MusicList> getMusicList() {
        return sqlSession.selectList("com.yovelas.mapper.MusicListMapper.getMusicList");
    }

    public MusicList loadMusicList(Map param) {
        return sqlSession.selectOne("com.yovelas.mapper.MusicListMapper.loadMusicList",param);
    }

    public List<Music> getMusicByList(Map param) {
        return sqlSession.selectList("com.yovelas.mapper.MusicListMapper.getUserMusicList",param);
    }
}
