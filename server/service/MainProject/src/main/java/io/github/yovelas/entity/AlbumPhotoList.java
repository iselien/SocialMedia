package io.github.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlbumPhotoList {
    private int id;
    private int albumId;
    private int photoId;
    private String createTime;
}
