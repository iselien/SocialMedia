package com.yovelas.dao;

import com.yovelas.entity.Album;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDao {

    @Autowired
    private SqlSession sqlSession;

    public int insertAlbum(Album album){
        return sqlSession.insert("com.yovelas.mapper.AlbumMapper.insertAlbum",album);
    }

    public List<Album> selectDiary(){
        return sqlSession.selectList("com.yovelas.mapper.AlbumMapper.selectAllAlbums");
    }
}


