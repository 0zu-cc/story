package com.storyxc.service.impl;

import com.storyxc.mapper.ArticleDao;
import com.storyxc.pojo.Article;
import com.storyxc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/18 15:39
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void addArticle(Article article) {
        articleDao.addArticle(article);
    }
}
