package io.github.yovelas.service;

import io.github.yovelas.dao.AlbumDao;
import io.github.yovelas.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumDao albumDao;

    @Autowired
    PhotosService photosService;

    public int insertAlbum(Album album){
        System.out.println(album);
        album.setCentralPhotoId(1);
        return albumDao.insertAlbum(album);
    }

    public int insertPhotoTag(PhotoTag tag){
        return albumDao.insertPhotoTag(tag);
    }

    public int insertAlbumTagRecord(PhotoAlbumTagRecord photoAlbumTagRecord){
        return albumDao.insertAlbumTagRecord(photoAlbumTagRecord);
    }

    public int insertAlbumPhotoList(PhotoAlbumPhotoList photoAlbumPhotoList){
        return albumDao.insertAlbumPhotoList(photoAlbumPhotoList);
    }

    public List<Album> selectAllAblums(){
        return albumDao.selectAllAblums();
    }

    public List<Album> selectAlbumsByUserId(int id) {
        return albumDao.selectAlbumsByUserId(id);
    }

    public List<PhotoTag> selectTagsByLikelyTag(String likelyTag) {
        return albumDao.selectTagsByLikelyTag(likelyTag);
    }
}
