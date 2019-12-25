package com.storyxc.service;

import com.storyxc.pojo.Category;

import java.util.List;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:11
 */
public interface CategoryService {
    List<Category> queryAllCategories();

    List<Map<String, Integer>> queryCategoryArticle();
}
