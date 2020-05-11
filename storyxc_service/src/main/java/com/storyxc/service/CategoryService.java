package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.Category;
import com.storyxc.pojo.QueryPageBean;

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

    PageInfo<Category> findPage(QueryPageBean queryPageBean);

    void addCategory(Category category);

    Category queryCategoryById(Integer id);

    void updateCategory(Category category);

    void deleteCategoryById(Integer id);
}
