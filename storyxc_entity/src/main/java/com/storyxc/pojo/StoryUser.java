package com.storyxc.pojo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/****
 * @Author:Xc
 * @Description:StoryUser构建
 * @Date
 *****/
@Table(name="story_user")
@Data
public class StoryUser implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

    @Column(name = "username")
	private String username;//

    @Column(name = "password")
	private String password;//

    @Column(name = "sex")
	private String sex;//性别，1男，0女

    @Column(name = "phone")
	private String phone;//

    @Column(name = "created_time")
	private Date createdTime;//创建时间

    @Column(name = "head_pic")
	private String headPic;//头像地址

    @Column(name = "last_login")
	private Date lastLogin;//最后登录时间


}
