package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.Article;
import com.storyxc.pojo.QueryPageBean;

import java.util.List;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/18 15:38
 */
public interface ArticleService {
    void addArticle(Article article,String type);

    PageInfo<Article> findPage(QueryPageBean queryPageBean);

    Map<String, Integer> queryBlogStat();

    List<Article> queryHotArticle();

    Article queryArticleById(String articleId);

    void updateViewCount(String id);

    void updateArticle(Article article);
}
