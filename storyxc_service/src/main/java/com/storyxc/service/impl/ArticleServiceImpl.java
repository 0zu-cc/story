package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.ArticleDao;
import com.storyxc.pojo.Article;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ArticleService;
import com.storyxc.util.DateUtil;
import com.storyxc.util.PageHelperUtil;
import com.storyxc.utils.StringFromHtmlUtil;
import com.youbenzi.mdtool.tool.MDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void addArticle(Article article, String type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String userCode = user.getUsername();
        article.setAuthorCode(userCode);
        article.setViewCount(article.getViewCount() == null ? 0 : article.getViewCount());
        if ("add".equals(type)) {
            article.setStatus("1");
        } else if ("save".equals(type)) {
            article.setStatus("0");
        }
        article.setCreateTime(DateUtil.parseDateToString(new Date()));
        //markdown->html->desc
        String str = StringFromHtmlUtil.getString(MDTool.markdown2Html(article.getArticleMain()));
        //获取摘要
        String desc = str.length() > 120 ? str.substring(0, 120) + "..." : str;
        article.setArticleDesc(desc);
        article.setArticleUrl("");
        if (article.getId() != null) {
            editArticle(article);
            articleDao.updateArticleStatus(article);
        } else {
            articleDao.addArticle(article);
            String url = "http://www.storyxc.com/article/" + article.getId();
            Map<String, String> map = new HashMap<>();
            map.put("id", article.getId().toString());
            map.put("url", url);
            articleDao.setArticleUrl(map);
            articleDao.setArticleCategory(article);
            if (article.getTagIds() != null && article.getTagIds().size() > 0) {
                articleDao.setArticleTag(article);
            }
        }
    }

    @Override
    public PageInfo<Article> findPage(QueryPageBean queryPageBean) {
        PageHelperUtil.startPage(queryPageBean);
        List<Article> articleList = articleDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<Article>(articleList);
    }

    @Override
    public Map<String, Integer> queryBlogStat() {
        return articleDao.queryBlogStat();
    }

    @Override
    public List<Article> queryHotArticle() {
        return articleDao.queryHotArticle();
    }

    @Override
    public Article queryArticleById(String articleId) {
        return articleDao.queryArticleById(articleId);
    }

    @Override
    public void updateViewCount(String id) {
        articleDao.updateViewCount(id);
    }

    @Override
    public void updateArticle(Article article) {
        if (article.getId() != null) {
            editArticle(article);
        } else {
            addArticle(article, "save");
        }
    }

    private void editArticle(Article article) {
        article.setEditTime(DateUtil.parseDateToString(new Date()));
        articleDao.updateArticle(article);
        articleDao.updateArticleCategory(article);
        if (article.getTagIds() != null && article.getTagIds().size() > 0) {
            articleDao.deleteArticleTag(article.getId());
            articleDao.setArticleTag(article);
        }
    }
}
