package com.storyxc.mapper;

import com.storyxc.pojo.Category;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:15
 */
public interface CategoryDao {
    List<Category> queryAllCategories();
}
