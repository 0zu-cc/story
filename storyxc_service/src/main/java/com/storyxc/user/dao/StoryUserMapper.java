package com.storyxc.user.dao;


import com.storyxc.pojo.StoryUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:Xc
 * @Description:StoryUser的Dao
 * @Date
 *****/
@Repository
@org.apache.ibatis.annotations.Mapper
public interface StoryUserMapper extends Mapper<StoryUser> {
}
