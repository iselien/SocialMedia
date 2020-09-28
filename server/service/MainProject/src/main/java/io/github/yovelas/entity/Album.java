package io.github.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Album {
    private int id;
    private int userId;
    private String albumName;
    private String albumTag;
    private String albumDescribe;
    private int centralPhotoId;
    private String cameraParameter;
    private String createTime;
}
