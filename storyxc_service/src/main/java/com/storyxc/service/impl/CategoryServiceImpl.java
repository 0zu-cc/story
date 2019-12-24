package com.storyxc.service.impl;

import com.storyxc.mapper.CategoryDao;
import com.storyxc.pojo.Category;
import com.storyxc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
