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
public class Article {
    private int id;
    private int userId;
    private String content;
    private String tags;
    private List articlePhotoList;
    private String createTime;
}
