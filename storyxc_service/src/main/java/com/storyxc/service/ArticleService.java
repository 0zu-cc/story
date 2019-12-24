package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.Article;
import com.storyxc.pojo.QueryPageBean;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/18 15:38
 */
public interface ArticleService {
    void addArticle(Article article);

    PageInfo<Article> findPage(QueryPageBean queryPageBean);
}
