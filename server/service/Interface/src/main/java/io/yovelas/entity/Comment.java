package io.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private int _id;
    private int objectId;
    private int userId;
    private int commentType;
    private String commentContent;
    private int commentStatus;
    private String commentTime;
    private List<Like> like;
    private List<Reply> reply;
}
