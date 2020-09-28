package io.github.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhotoAlbumPhotoList {
    private int id;
    private int album_Id;
    private int photoId;
    private String createTime;
}
