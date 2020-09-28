package io.github.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhotoRecord {
    private int id;
    private int userId;
    private String fileName;
    private String recordName;
    private String url;
    private String descript;
    private String tag;
    private String createTime;
    private boolean error;
}
