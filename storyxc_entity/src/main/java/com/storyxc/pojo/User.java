package com.storyxc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/****
 * @Author:Xc
 * @Description:StoryUser构建
 * @Date
 *****/

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private String email;
    private String createdTime;
    private String headPic;
    private String lastLogin;
    private String lastLoginIp;
    private List<Role> roles;
}
