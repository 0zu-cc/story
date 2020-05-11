package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.CategoryDao;
import com.storyxc.pojo.Category;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.CategoryService;
import com.storyxc.util.PageHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:11
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> queryAllCategories() {
        return categoryDao.queryAllCategories();
    }

    @Override
    public List<Map<String, Integer>> queryCategoryArticle() {
        return categoryDao.queryCategoryArticle();
    }

    @Override
    public PageInfo<Category> findPage(QueryPageBean queryPageBean) {
        PageHelperUtils.startPage(queryPageBean);
        List<Category> categoryList = categoryDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<>(categoryList);
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    public Category queryCategoryById(Integer id) {
        return categoryDao.queryCategoryById(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        //删除文章分类和文章的映射关系
        categoryDao.deleteCategoryArticleMapping(id);
        //删除文章分类
        categoryDao.deleteCategoryById(id);
    }

}
