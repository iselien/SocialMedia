package com.yovelas.dao;

import com.yovelas.entity.Music;
import com.yovelas.entity.Photo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PhotosDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertPhoto(Photo photo) {
		return sqlSession.insert("com.yovelas.mapper.PhotosMapper.insertPhoto",photo);
	}

	public List<Photo> selecrtPhoto() {
		return sqlSession.selectList("com.yovelas.mapper.PhotosMapper.selectPhotos");
	}

	public Photo selectPhotoByFileName(String fileName) {
		return sqlSession.selectOne("com.yovelas.mapper.PhotosMapper.selectPhotoByFileName",fileName);
	}
}
