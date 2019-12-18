package com.storyxc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/****
 * @Author:Xc
 * @Description:StoryArticle构建
 * @Date
 *****/
@Data
public class Article implements Serializable{
	private Integer id;
	private String articleTitle;
	private String articleCatagory;
	private String articleMain;
	private String articleDesc;
	private String articleUrl;
	private String authorCode;
	private Date createTime;
	private Date editTime;
	private Integer viewCount;
	private Integer commentCount;
	private String status;
	private List<Integer> tagIds;
}
