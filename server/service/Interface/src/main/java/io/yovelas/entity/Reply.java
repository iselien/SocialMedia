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
public class Reply {
    private int id;
    private int userId;
    private int replayId;
    private String commentContent;
    private int commentStatus;
    private String commentTime;
    private List<Like> like;
}
