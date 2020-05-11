package com.storyxc.mapper;

import com.storyxc.pojo.Category;

import java.util.List;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:15
 */
public interface CategoryDao {
    List<Category> queryAllCategories();

    List<Map<String, Integer>> queryCategoryArticle();

    List<Category> findPage(String queryString);

    void addCategory(Category category);

    Category queryCategoryById(Integer id);

    void updateCategory(Category category);

    void deleteCategoryArticleMapping(Integer id);

    void deleteCategoryById(Integer id);
}
