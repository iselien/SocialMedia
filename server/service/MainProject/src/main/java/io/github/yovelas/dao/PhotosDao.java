package io.github.yovelas.dao;

import io.github.yovelas.entity.Photo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotosDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertPhoto(Photo photo) {
		return sqlSession.insert("io.github.yovelas.mapper.PhotosMapper.insertPhoto",photo);
	}

	public int updatePhoto(Photo photo) {
		return sqlSession.update("io.github.yovelas.mapper.PhotosMapper.updatePhoto",photo);
	}
	public List<Photo> selecrtPhoto() {
		return sqlSession.selectList("io.github.yovelas.mapper.PhotosMapper.selectPhotos");
	}
	public Photo selectPhotoById(int id) {
		return sqlSession.selectOne("io.github.yovelas.mapper.PhotosMapper.selectPhotoById",id);
	}
	public Photo selectPhotoByFileName(String fileName) {
		return sqlSession.selectOne("io.github.yovelas.mapper.PhotosMapper.selectPhotoByFileName",fileName);
	}
}
