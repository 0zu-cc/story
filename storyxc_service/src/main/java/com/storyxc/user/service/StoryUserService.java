package com.storyxc.user.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.StoryUser;

import java.util.List;
/****
 * @Author:Xc
 * @Description:StoryUser业务层接口
 * @Date
 *****/
public interface StoryUserService {

    /***
     * StoryUser多条件分页查询
     * @param storyUser
     * @param page
     * @param size
     * @return
     */
    PageInfo<StoryUser> findPage(StoryUser storyUser, int page, int size);

    /***
     * StoryUser分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<StoryUser> findPage(int page, int size);

    /***
     * StoryUser多条件搜索方法
     * @param storyUser
     * @return
     */
    List<StoryUser> findList(StoryUser storyUser);

    /***
     * 删除StoryUser
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改StoryUser数据
     * @param storyUser
     */
    void update(StoryUser storyUser);

    /***
     * 新增StoryUser
     * @param storyUser
     */
    void add(StoryUser storyUser);

    /**
     * 根据ID查询StoryUser
     * @param id
     * @return
     */
     StoryUser findById(Integer id);

    /***
     * 查询所有StoryUser
     * @return
     */
    List<StoryUser> findAll();
}
