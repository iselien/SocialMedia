package com.yovelas.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.yovelas.AppConfig;
import com.yovelas.dao.PhotosDao;
import com.yovelas.dao.PhotosThumbnailDao;
import com.yovelas.entity.Photo;
import com.yovelas.entity.PhotoThumbnail;
import com.yovelas.entity.Progress;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class PhotosService {

    private static final Logger log = LogManager.getLogger(PhotosService.class);

    @Autowired
    PhotosDao photosDao;

    @Autowired
    AppConfig appConfig;

    @Autowired
    PhotosThumbnailDao photosThumbnailDao;

    @Autowired
    StringRedisTemplate redisTemplate;

    public void retrieval() throws IOException {

        ValueOperations valueOperations = redisTemplate.opsForValue();

        File director = new File(appConfig.getDataDirectory() + "photos");
        Progress progress = new Progress();

        if (director.canRead() && director.isDirectory()) {

            log.info("File list length:" + director.listFiles().length);
            log.info("Fetch metadata and insert metadata to database");

            progress.setName("Fetch metadata");
            progress.setTotal(director.listFiles().length);
            progress.setCurrent(0);

            valueOperations.set("progress",progress.toJSON());

            for (File file : director.listFiles()) {
                progress.increase();
                if (file.getName().split("")[0].equals(".")) {
                    continue;
                }
                Photo photo = fetchMatedata(file);
                log.trace(photo.toString());

                valueOperations.set("progress",progress.toJSON());

                if (checkRepeat(photo)) {
                    photosDao.insertPhoto(photo);
                }
            }
            progress.setCurrent(-1);
            log.info("Fetch metadata finish");
        } else {
            log.error(director.getAbsolutePath() + " cannot be data directory");
        }

        checkThumbnail();
    }

    public List<Photo> selecrtPhoto() {
        return photosDao.selecrtPhoto();
    }

    public boolean checkRepeat(Photo photo) {
        for (Photo p : photosDao.selecrtPhoto()) {
            if (p.getFileName().equals(photo.getFileName())) {
                return false;
            }
        }
        return true;
    }

    public Photo fetchMatedata(File file) {

        if (file.isFile() && file.canRead() && !file.getName().split("")[0].equals(".")) {
            try {
//                System.out.println();
//                System.out.println(file.getName());
//                System.out.println(ImageIO.read(file));

                Metadata metadata = ImageMetadataReader.readMetadata(file);
                Photo photo = new Photo();

                for (Directory directory : metadata.getDirectories()) {
                    for (Tag tag : directory.getTags()) {
//                                System.out.println(tag.getTagName()+":"+tag.getDescription());
                        if (tag.getTagName().equals("File Name")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setFileName(tag.getDescription());
                        } else if (tag.getTagName().equals("File Size")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setFileSize(tag.getDescription());
                        } else if (tag.getTagName().equals("Data/Time")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setDateTime(tag.getDescription());
                        } else if (tag.getTagName().equals("Image Width")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setImageWidth(tag.getDescription());
                        } else if (tag.getTagName().equals("Image Height")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setImageHeight(tag.getDescription());
                        } else if (tag.getTagName().equals("Aritst")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setArtist(tag.getDescription());
                        } else if (tag.getTagName().equals("User Comment")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setUserComment(tag.getDescription());
                        } else if (tag.getTagName().equals("Exposure Time")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setExposureTime(tag.getDescription());
                        } else if (tag.getTagName().equals("F-Number")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setFNumber(tag.getDescription());
                        } else if (tag.getTagName().equals("ISO SPeed Ratings")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setIsoSpeedRatings(tag.getDescription());
                        } else if (tag.getTagName().equals("Focal Length")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setFocalLength(tag.getDescription());
                        } else if (tag.getTagName().equals("Metering Mode")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setMeteringMode(tag.getDescription());
                        } else if (tag.getTagName().equals("Exposure Program")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setExposureProgram(tag.getDescription());
                        } else if (tag.getTagName().equals("Flash")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setFlash(tag.getDescription());
                        } else if (tag.getTagName().equals("Make")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setMake(tag.getDescription());
                        } else if (tag.getTagName().equals("Model")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setModel(tag.getDescription());
                        } else if (tag.getTagName().equals("Long Focal Length")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setLongFocalLength(tag.getDescription());
                        } else if (tag.getTagName().equals("Short Focal Length")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setShortFocalLength(tag.getDescription());
                        } else if (tag.getTagName().equals("Aperture Value")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setApertureValue(tag.getDescription());
                        } else if (tag.getTagName().equals("Software")) {
//                            System.out.println(tag.getTagName() + ":" + tag.getDescription());
                            photo.setSoftware(tag.getDescription());
                        }
                    }
                }
//                System.out.println(photo);
//                        System.out.println(photosService.insertPhotos(photo));
                return photo;

            } catch (Exception e) {
                System.err.println("it is not a pictures or get pictures metadata fail," + e);
                log.error("it is not a pictures or get pictures metadata fail:" + file.getAbsolutePath());
            }
        }
        return null;
    }

    public void checkThumbnail() {

        ValueOperations valueOperations = redisTemplate.opsForValue();
        List<Photo> list = photosDao.selecrtPhoto();
        Progress progress = new Progress();
        progress.setName("generate Thumbnail");
        progress.setTotal(list.size());
        progress.setCurrent(0);

        for (Photo p : list) {
            PhotoThumbnail pt = photosThumbnailDao.selectPhotoThumbnailByPhotoId(p.getId());

            if (pt == null) {
                generateThumbnail(new File(appConfig.getDataDirectory() + "photos/" + p.getFileName()),p.getId());
            }
            progress.increase();

            valueOperations.set("progress",progress.toJSON());

            log.trace(p);
        }
        log.info("generate thumbnail finish");
    }

    public void generateThumbnail(File file,int id) {
        File thumbnail_1920x1080 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1920x1080-" + file.getName());
        File thumbnail_1024x768 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1024x768-" + file.getName());
        File thumbnail_800x600 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_800x600-" + file.getName());
        File thumbnail_500x375 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_500x375-" + file.getName());
        File thumbnail_400x300 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_400x300-" + file.getName());

        try {
            // Scale to original scale
            Thumbnails.of(file).size(1920, 1080).toOutputStream(new FileOutputStream(thumbnail_1920x1080));
            Thumbnails.of(file).size(1024, 768).toOutputStream(new FileOutputStream(thumbnail_1024x768));
            Thumbnails.of(file).size(800, 600).toOutputStream(new FileOutputStream(thumbnail_800x600));
            Thumbnails.of(file).size(500, 375).toOutputStream(new FileOutputStream(thumbnail_500x375));
            Thumbnails.of(file).size(400, 300).toOutputStream(new FileOutputStream(thumbnail_400x300));


            PhotoThumbnail photoThumbnail = new PhotoThumbnail();
            photoThumbnail.setPhotoId(id);
            photoThumbnail.setThumbnail_1920x1080(thumbnail_1920x1080.getName());
            photoThumbnail.setThumbnail_1024x768(thumbnail_1024x768.getName());
            photoThumbnail.setThumbnail_800x600(thumbnail_800x600.getName());
            photoThumbnail.setThumbnail_500x375(thumbnail_500x375.getName());
            photoThumbnail.setThumbnail_400x300(thumbnail_400x300.getName());
            photosThumbnailDao.insertPhotoThumbnail(photoThumbnail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Photo selectPhotoByFileName(String filename) {
        return photosDao.selectPhotoByFileName(filename);
    }

}