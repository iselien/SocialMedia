package io.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int userId;
    private String userAccount;
    private String userPassword;
    private String userNickname;
    private String userIntroduction;
    private String userEmail;
    private String userPhone;
    private String userImage;
    private String userStatus;
    private String userCreateTime;
}
