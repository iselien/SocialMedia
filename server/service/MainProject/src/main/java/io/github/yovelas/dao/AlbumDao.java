package io.github.yovelas.dao;

import io.github.yovelas.entity.Album;
import io.github.yovelas.entity.PhotoAlbumPhotoList;
import io.github.yovelas.entity.PhotoAlbumTagRecord;
import io.github.yovelas.entity.PhotoTag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDao {

    @Autowired
    private SqlSession sqlSession;

    public int insertAlbum(Album album){
        return sqlSession.insert("io.github.yovelas.mapper.AlbumMapper.insertAlbum",album);
    }

    public int insertAlbumTagRecord(PhotoAlbumTagRecord photoAlbumTagRecord){
        return sqlSession.insert("io.github.yovelas.mapper.AlbumMapper.insertAlbum", photoAlbumTagRecord);
    }

    public int insertAlbumPhotoList(PhotoAlbumPhotoList photoAlbumPhotoList){
        return sqlSession.insert("io.github.yovelas.mapper.AlbumMapper.insertAlbumPhotoList", photoAlbumPhotoList);
    }

    public int insertPhotoTag(PhotoTag tag){
        return sqlSession.insert("io.github.yovelas.mapper.AlbumMapper.insertPhotoTag",tag);
    }

    public List<Album> selectAllAblums(){
        return sqlSession.selectList("io.github.yovelas.mapper.AlbumMapper.selectAllAlbums");
    }

    public List<Album> selectAlbumsByUserId(int id) {
        return sqlSession.selectList("io.github.yovelas.mapper.AlbumMapper.selectAlbumsByUserId",id);
    }

    public List<PhotoTag> selectTagsByLikelyTag(String likelyTag) {
        return sqlSession.selectList("io.github.yovelas.mapper.AlbumMapper.selectTagsByLikelyTag",likelyTag);
    }

}


