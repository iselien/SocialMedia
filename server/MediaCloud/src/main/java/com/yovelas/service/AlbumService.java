package com.yovelas.service;

import com.yovelas.dao.AlbumDao;
import com.yovelas.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumDao albumDao;

    @Autowired
    PhotosService photosService;

    public int insertDiary(Album album){
        System.out.println(album);
        album.setCentralPhotoId(1);
        return albumDao.insertAlbum(album);
    }

    public List<Album> selectDiary(){
        return albumDao.selectDiary();
    }
}
