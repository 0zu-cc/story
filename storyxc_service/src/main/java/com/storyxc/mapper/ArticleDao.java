package com.storyxc.mapper;

import com.storyxc.pojo.Article;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/18 15:53
 */
public interface ArticleDao {
    void addArticle(Article article);

    void setArticleCategory(Article article);

    void setArticleTag(Article article);

    List<Article> findPage(String queryString);
}
