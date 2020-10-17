package com.yovelas.service;

import com.yovelas.dao.MusicListDao;
import com.yovelas.entity.MusicList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MusicListService {
    @Autowired
    MusicListDao musicListDao;

    public List<MusicList> getUserMusicList(Map map) {
       return musicListDao.getUserMusicList(map);
    }

}
