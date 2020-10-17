package com.yovelas.entity;

import com.mysql.cj.jdbc.Blob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhotoThumbnail {
    private int id;
    private int photoId;
    private String thumbnail_1920x1080;
    private String thumbnail_1024x768;
    private String thumbnail_800x600;
    private String thumbnail_500x375;
    private String thumbnail_400x300;
}
