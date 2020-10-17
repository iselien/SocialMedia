package com.yovelas.dao;

import com.yovelas.entity.Photo;
import com.yovelas.entity.PhotoThumbnail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotosThumbnailDao {

    @Autowired
    private SqlSession sqlSession;


    public PhotoThumbnail selectPhotoThumbnailByPhotoId(int photoid) {
        return sqlSession.selectOne("com.yovelas.mapper.PhotoThumbnailMapper.selectPhotoThumbnailByPhotoId", photoid);
    }


    public int insertPhotoThumbnail(PhotoThumbnail photoThumbnail) {
        return sqlSession.insert("com.yovelas.mapper.PhotoThumbnailMapper.insertPhotoThumbnail", photoThumbnail);
    }

    public int insertPhoto(Photo photo) {
        return sqlSession.insert("com.yovelas.mapper.PhotosMapper.insertPhoto", photo);
    }

    public List<Photo> selecrtPhoto() {
        return sqlSession.selectList("com.yovelas.mapper.PhotosMapper.selectPhotos");
    }
}
