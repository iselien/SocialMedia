package com.yovelas.dao;

import com.yovelas.entity.Music;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MusicDao {

	@Autowired
	private SqlSession sqlSession;

	public List<Music> loadMusicByList(Map param) {
		return sqlSession.selectList("com.yovelas.mapper.MusicMapper.loadMusicByList",param);
	}

	public int insertMusic(Music music) {
		return sqlSession.insert("com.yovelas.music.mapper.MusicMapper.insertMusic",music);
	}

}
