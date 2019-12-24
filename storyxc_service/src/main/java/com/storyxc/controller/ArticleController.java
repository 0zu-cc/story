package com.storyxc.controller;

import com.github.pagehelper.PageInfo;
import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Article;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/18 15:25
 */
@RestController
@RequestMapping("/story/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageInfo<Article> result = articleService.findPage(queryPageBean);
        return new Result(true,StatusCode.OK, "查询文章列表成功", result);
    }

    @PreAuthorize("hasAnyAuthority('ARTICLE_ADD')")
    @PostMapping
    public Result addArticle(@RequestBody Article article){
        articleService.addArticle(article);
        return new Result(true, StatusCode.OK,"文章发布成功");
    }

}
