package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Category;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageInfo<Category> categoryList = categoryService.findPage(queryPageBean);
        return new Result(true,StatusCode.OK,"查询分类列表成功",categoryList);
    }

    @PostMapping
    public Result addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return new Result(true,StatusCode.OK,"新增文章分类成功");
    }

    @GetMapping("/{id}")
    public Result queryCategoryById(@PathVariable Integer id){
        Category category = categoryService.queryCategoryById(id);
        return new Result(true,StatusCode.OK,"查询分类信息成功",category);
    }

    @PutMapping
    public Result updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return new Result(true,StatusCode.OK,"修改分类信息成功");
    }

    @DeleteMapping
    public Result deleteCategoryById(Integer id){
        categoryService.deleteCategoryById(id);
        return new Result(true,StatusCode.OK,"删除文章分类成功");
    }

}
