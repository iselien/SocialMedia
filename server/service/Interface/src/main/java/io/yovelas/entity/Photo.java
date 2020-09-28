package io.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Photo {
    private int photoId;
    private String photoFileName;
    private String photoFileOriginName;
    private String photoThumbnail1920x1080;
    private String photoThumbnail1024x768;
    private String photoThumbnail800x600;
    private String photoThumbnail500x375;
    private String photoThumbnail400x300;
    private String photoFileSize;
    private String photoImageWidth;
    private String photoImageHeight;
    private String photoDateTime;
    private String photoArtist;
    private String photoImageSize;
    private String photoUserComment;
    private String photoExposureTime;
    private String photoFNumber;
    private String photoIsoSpeedRatings;
    private String photoFocalLength;
    private String photoMeteringMode;
    private String photoExposureMode;
    private String photoExposureProgram;
    private String photoFlash;
    private String photoMake;
    private String photoModel;
    private String photoLongFocalLength ;
    private String photoShortFocalLength;
    private String photoApertureValue;
    private String photoSoftware;
}
