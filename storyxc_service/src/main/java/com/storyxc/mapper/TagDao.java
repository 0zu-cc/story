package com.storyxc.mapper;

import com.storyxc.pojo.Tag;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:07
 */
public interface TagDao {
    List<Tag> queryAllTags();
}
