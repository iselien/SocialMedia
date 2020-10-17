package com.yovelas.service;

import com.yovelas.AppConfig;
import com.yovelas.dao.PhotosThumbnailDao;
import com.yovelas.entity.PhotoThumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PhotoThumbnailService {

    @Autowired
    PhotosThumbnailDao photosThumbnailDao;

    @Autowired
    AppConfig appConfig;

    public File getThumbnail(int photoid, String size) {
        PhotoThumbnail photoThumbnail = photosThumbnailDao.selectPhotoThumbnailByPhotoId(photoid);

        File file = null;
        switch (size){
            case "1920x1080":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photoThumbnail.getThumbnail_1920x1080());
                break;
            case "1024x768":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photoThumbnail.getThumbnail_1024x768());
                break;
            case "800x600":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photoThumbnail.getThumbnail_800x600());
                break;
            case "500x600":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photoThumbnail.getThumbnail_500x375());
                break;
            case "400x300":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photoThumbnail.getThumbnail_400x300());
                break;
        }

        System.out.println(file.getAbsolutePath());
        return file;
    }
}
