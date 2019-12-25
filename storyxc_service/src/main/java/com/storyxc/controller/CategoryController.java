package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Category;
import com.storyxc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:10
 */
@RestController
@RequestMapping("/story/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result queryAllCategories(){
        List<Category> categoryList = categoryService.queryAllCategories();
        return new Result(true, StatusCode.OK,"查询分类列表成功",categoryList);
    }

    @GetMapping("/article")
    public Result queryCategoryArticle(){
        List<Map<String, Integer>> mapList = categoryService.queryCategoryArticle();
        return new Result(true,StatusCode.OK,"查询分类下文章数成功",mapList);
    }

}
