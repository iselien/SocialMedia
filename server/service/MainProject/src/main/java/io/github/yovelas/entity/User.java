package io.github.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String image;
    private String createTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
