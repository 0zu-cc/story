package com.storyxc.mapper;

import com.storyxc.pojo.Article;

import java.util.List;
import java.util.Map;

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

    Map<String, Integer> queryBlogStat();

    List<Article> queryHotArticle();

    Article queryArticleById(String articleId);

    void setArticleUrl(Map<String, String> map);

    void updateViewCount(String id);
}
