package com.yovelas.entity;

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
    private String albumTag;
    private String albumDescribe;
    private String viewUserIdList;
    private String likeUserIdList;
    private String commentIdList;
    private int centralPhotoId;
    private String photoList;
    private String cameraParameter;
    private String createTime;

}
