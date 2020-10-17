package com.yovelas.service;

import com.yovelas.dao.MusicDao;
import com.yovelas.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MusicService {

    @Autowired
    MusicDao musicDao;
    public List<Music> loadMusicByList(Map map) {
        return musicDao.loadMusicByList(map);
    }
}
