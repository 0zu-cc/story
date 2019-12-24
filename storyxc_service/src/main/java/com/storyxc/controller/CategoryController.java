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

}
