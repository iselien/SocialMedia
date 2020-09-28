package io.github.yovelas.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import io.github.yovelas.config.AppConfig;
import io.github.yovelas.dao.PhotosDao;
import io.github.yovelas.entity.Photo;
import io.github.yovelas.entity.Progress;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    StringRedisTemplate redisTemplate;

    public int insertPhoto(Photo photo) {
        return photosDao.insertPhoto(photo);
    }
    public List<Photo> selecrtPhoto() {
        return photosDao.selecrtPhoto();
    }
    public Photo selectPhotoByFileName(String filename) {
        return photosDao.selectPhotoByFileName(filename);
    }
    public void generateMetadata(){
        // 要据photos表格生产照片元数据，最后将photos表格中不存在的文件元文件删除
        List<Photo> photos = photosDao.selecrtPhoto();
        for (Photo p : photos){
            if(p.getImageWidth()!=null){
               continue;
            } else{
                System.out.println("检测："+p);
                try {
                    Metadata metadata = ImageMetadataReader.readMetadata(new File(appConfig.getDataDirectory() + "photos/" + p.getFileName()));
                    for (Directory directory : metadata.getDirectories()) {
                        for (Tag tag : directory.getTags()) {
                            if (tag.getTagName().equals("File Name")) {
                                p.setFileName(tag.getDescription());
                            } else if (tag.getTagName().equals("File Size")) {
                                p.setFileSize(tag.getDescription());
                            } else if (tag.getTagName().equals("Data/Time")) {
                                p.setDateTime(tag.getDescription());
                            } else if (tag.getTagName().equals("Image Width")) {
                                p.setImageWidth(tag.getDescription());
                            } else if (tag.getTagName().equals("Image Height")) {
                                p.setImageHeight(tag.getDescription());
                            } else if (tag.getTagName().equals("Aritst")) {
                                p.setArtist(tag.getDescription());
                            } else if (tag.getTagName().equals("User Comment")) {
                                p.setUserComment(tag.getDescription());
                            } else if (tag.getTagName().equals("Exposure Time")) {
                                p.setExposureTime(tag.getDescription());
                            } else if (tag.getTagName().equals("F-Number")) {
                                p.setFNumber(tag.getDescription());
                            } else if (tag.getTagName().equals("ISO SPeed Ratings")) {
                                p.setIsoSpeedRatings(tag.getDescription());
                            } else if (tag.getTagName().equals("Focal Length")) {
                                p.setFocalLength(tag.getDescription());
                            } else if (tag.getTagName().equals("Metering Mode")) {
                                p.setMeteringMode(tag.getDescription());
                            } else if (tag.getTagName().equals("Exposure Program")) {
                                p.setExposureProgram(tag.getDescription());
                            } else if (tag.getTagName().equals("Flash")) {
                                p.setFlash(tag.getDescription());
                            } else if (tag.getTagName().equals("Make")) {
                                p.setMake(tag.getDescription());
                            } else if (tag.getTagName().equals("Model")) {
                                p.setModel(tag.getDescription());
                            } else if (tag.getTagName().equals("Long Focal Length")) {
                                p.setLongFocalLength(tag.getDescription());
                            } else if (tag.getTagName().equals("Short Focal Length")) {
                                p.setShortFocalLength(tag.getDescription());
                            } else if (tag.getTagName().equals("Aperture Value")) {
                                p.setApertureValue(tag.getDescription());
                            } else if (tag.getTagName().equals("Software")) {
                                p.setSoftware(tag.getDescription());
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("it is not a pictures or get pictures metadata fail," + e);
                    log.error("it is not a pictures or get pictures metadata fail");
                }

                File thumbnail_1920x1080 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1920x1080-" + p.getFileName());
                File thumbnail_1024x768 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1024x768-" + p.getFileName());
                File thumbnail_800x600 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_800x600-" + p.getFileName());
                File thumbnail_500x375 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_500x375-" + p.getFileName());
                File thumbnail_400x300 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_400x300-" + p.getFileName());

                try {
                    // Scale to original scale
                    Thumbnails.of(appConfig.getDataDirectory() + "photos/" + p.getFileName()).size(1920, 1080).toOutputStream(new FileOutputStream(thumbnail_1920x1080));
                    Thumbnails.of(appConfig.getDataDirectory() + "photos/" + p.getFileName()).size(1024, 768).toOutputStream(new FileOutputStream(thumbnail_1024x768));
                    Thumbnails.of(appConfig.getDataDirectory() + "photos/" + p.getFileName()).size(800, 600).toOutputStream(new FileOutputStream(thumbnail_800x600));
                    Thumbnails.of(appConfig.getDataDirectory() + "photos/" + p.getFileName()).size(500, 375).toOutputStream(new FileOutputStream(thumbnail_500x375));
                    Thumbnails.of(appConfig.getDataDirectory() + "photos/" + p.getFileName()).size(400, 300).toOutputStream(new FileOutputStream(thumbnail_400x300));

                    p.setThumbnail1920x1080(thumbnail_1920x1080.getName());
                    p.setThumbnail1024x768(thumbnail_1920x1080.getName());
                    p.setThumbnail800x600(thumbnail_800x600.getName());
                    p.setThumbnail500x375(thumbnail_500x375.getName());
                    p.setThumbnail400x300(thumbnail_400x300.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("okk:"+p);
                photosDao.updatePhoto(p);

            }
        }
    }

    public File thumbnail(String size, int id){
        Photo photo = photosDao.selectPhotoById(id);
        if(photo == null) return null;

        File file = null;
        switch (size){
            case "1920x1080":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photo.getThumbnail1920x1080());
                break;
            case "1024x768":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photo.getThumbnail1024x768());
                break;
            case "800x600":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photo.getThumbnail800x600());
                break;
            case "500x600":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photo.getThumbnail500x375());
                break;
            case "400x300":
                file = new File(appConfig.getDataDirectory() + "thumbnail/" + photo.getThumbnail400x300());
                break;
        }
        return file;
    }








    // ----------------
    private void retrieval(Photo photo) {
        fetchMatedata(photo);
        generateThumbnail(photo);
    }

    private void fetchMatedata(Photo photo) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(appConfig.getDataDirectory() + "photos/" + photo.getFileName()));
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if (tag.getTagName().equals("File Name")) {
                        photo.setFileName(tag.getDescription());
                    } else if (tag.getTagName().equals("File Size")) {
                        photo.setFileSize(tag.getDescription());
                    } else if (tag.getTagName().equals("Data/Time")) {
                        photo.setDateTime(tag.getDescription());
                    } else if (tag.getTagName().equals("Image Width")) {
                        photo.setImageWidth(tag.getDescription());
                    } else if (tag.getTagName().equals("Image Height")) {
                        photo.setImageHeight(tag.getDescription());
                    } else if (tag.getTagName().equals("Aritst")) {
                        photo.setArtist(tag.getDescription());
                    } else if (tag.getTagName().equals("User Comment")) {
                        photo.setUserComment(tag.getDescription());
                    } else if (tag.getTagName().equals("Exposure Time")) {
                        photo.setExposureTime(tag.getDescription());
                    } else if (tag.getTagName().equals("F-Number")) {
                        photo.setFNumber(tag.getDescription());
                    } else if (tag.getTagName().equals("ISO SPeed Ratings")) {
                        photo.setIsoSpeedRatings(tag.getDescription());
                    } else if (tag.getTagName().equals("Focal Length")) {
                        photo.setFocalLength(tag.getDescription());
                    } else if (tag.getTagName().equals("Metering Mode")) {
                        photo.setMeteringMode(tag.getDescription());
                    } else if (tag.getTagName().equals("Exposure Program")) {
                        photo.setExposureProgram(tag.getDescription());
                    } else if (tag.getTagName().equals("Flash")) {
                        photo.setFlash(tag.getDescription());
                    } else if (tag.getTagName().equals("Make")) {
                        photo.setMake(tag.getDescription());
                    } else if (tag.getTagName().equals("Model")) {
                        photo.setModel(tag.getDescription());
                    } else if (tag.getTagName().equals("Long Focal Length")) {
                        photo.setLongFocalLength(tag.getDescription());
                    } else if (tag.getTagName().equals("Short Focal Length")) {
                        photo.setShortFocalLength(tag.getDescription());
                    } else if (tag.getTagName().equals("Aperture Value")) {
                        photo.setApertureValue(tag.getDescription());
                    } else if (tag.getTagName().equals("Software")) {
                        photo.setSoftware(tag.getDescription());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("it is not a pictures or get pictures metadata fail," + e);
            log.error("it is not a pictures or get pictures metadata fail");
        }
    }

    private void generateThumbnail(Photo photo) {
        File thumbnail_1920x1080 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1920x1080-" + photo.getFileName());
        File thumbnail_1024x768 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1024x768-" + photo.getFileName());
        File thumbnail_800x600 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_800x600-" + photo.getFileName());
        File thumbnail_500x375 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_500x375-" + photo.getFileName());
        File thumbnail_400x300 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_400x300-" + photo.getFileName());

        try {
            // Scale to original scale
            Thumbnails.of(appConfig.getDataDirectory() + "photos/" + photo.getFileName()).size(1920, 1080).toOutputStream(new FileOutputStream(thumbnail_1920x1080));
            Thumbnails.of(appConfig.getDataDirectory() + "photos/" + photo.getFileName()).size(1024, 768).toOutputStream(new FileOutputStream(thumbnail_1024x768));
            Thumbnails.of(appConfig.getDataDirectory() + "photos/" + photo.getFileName()).size(800, 600).toOutputStream(new FileOutputStream(thumbnail_800x600));
            Thumbnails.of(appConfig.getDataDirectory() + "photos/" + photo.getFileName()).size(500, 375).toOutputStream(new FileOutputStream(thumbnail_500x375));
            Thumbnails.of(appConfig.getDataDirectory() + "photos/" + photo.getFileName()).size(400, 300).toOutputStream(new FileOutputStream(thumbnail_400x300));

            photo.setThumbnail1920x1080(thumbnail_1920x1080.getName());
            photo.setThumbnail1024x768(thumbnail_1920x1080.getName());
            photo.setThumbnail800x600(thumbnail_800x600.getName());
            photo.setThumbnail500x375(thumbnail_500x375.getName());
            photo.setThumbnail400x300(thumbnail_400x300.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

//        checkThumbnail();
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

//    public void checkThumbnail() {
//
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        List<Photo> list = photosDao.selecrtPhoto();
//        Progress progress = new Progress();
//        progress.setName("generate Thumbnail");
//        progress.setTotal(list.size());
//        progress.setCurrent(0);
//
//        for (Photo p : list) {
//            PhotoThumbnail pt = photosThumbnailDao.selectPhotoThumbnailByPhotoId(p.getId());
//
//            if (pt == null) {
//                generateThumbnail(new File(appConfig.getDataDirectory() + "photos/" + p.getFileName()),p.getId());
//            }
//            progress.increase();
//
//            valueOperations.set("progress",progress.toJSON());
//
//            log.trace(p);
//        }
//        log.info("generate thumbnail finish");
//    }
//
//    public void generateThumbnail(File file,int id) {
//        File thumbnail_1920x1080 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1920x1080-" + file.getName());
//        File thumbnail_1024x768 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_1024x768-" + file.getName());
//        File thumbnail_800x600 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_800x600-" + file.getName());
//        File thumbnail_500x375 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_500x375-" + file.getName());
//        File thumbnail_400x300 = new File(appConfig.getDataDirectory() + "/thumbnail/thumbnail_400x300-" + file.getName());
//
//        try {
//            // Scale to original scale
//            Thumbnails.of(file).size(1920, 1080).toOutputStream(new FileOutputStream(thumbnail_1920x1080));
//            Thumbnails.of(file).size(1024, 768).toOutputStream(new FileOutputStream(thumbnail_1024x768));
//            Thumbnails.of(file).size(800, 600).toOutputStream(new FileOutputStream(thumbnail_800x600));
//            Thumbnails.of(file).size(500, 375).toOutputStream(new FileOutputStream(thumbnail_500x375));
//            Thumbnails.of(file).size(400, 300).toOutputStream(new FileOutputStream(thumbnail_400x300));
//
//
//            PhotoThumbnail photoThumbnail = new PhotoThumbnail();
//            photoThumbnail.setPhotoId(id);
//            photoThumbnail.setThumbnail_1920x1080(thumbnail_1920x1080.getName());
//            photoThumbnail.setThumbnail_1024x768(thumbnail_1024x768.getName());
//            photoThumbnail.setThumbnail_800x600(thumbnail_800x600.getName());
//            photoThumbnail.setThumbnail_500x375(thumbnail_500x375.getName());
//            photoThumbnail.setThumbnail_400x300(thumbnail_400x300.getName());
//            photosThumbnailDao.insertPhotoThumbnail(photoThumbnail);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}