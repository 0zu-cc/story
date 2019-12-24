package com.storyxc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.ArticleDao;
import com.storyxc.pojo.Article;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ArticleService;
import com.storyxc.util.DateUtil;
import com.storyxc.utils.StringFromHtmlUtil;
import com.youbenzi.mdtool.tool.MDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String userCode = user.getUsername();
        article.setAuthorCode(userCode);
        //TODO 集成模板引擎生成静态页
        article.setArticleUrl("test");
        article.setViewCount(0);
        article.setStatus("1");
        article.setCommentCount(0);
        article.setCreateTime(DateUtil.parseDateToString(new Date()));
        //markdown->html->desc
        String str = StringFromHtmlUtil.getString(MDTool.markdown2Html(article.getArticleMain()));
        //获取摘要  摘要长度为255个字符
        String desc = str.length() > 240 ? str.substring(0, 240) + "......" : str;
        article.setArticleDesc(desc);
        articleDao.addArticle(article);
        articleDao.setArticleCategory(article);
        articleDao.setArticleTag(article);
    }

    @Override
    public PageInfo<Article> findPage(QueryPageBean queryPageBean) {
        if (queryPageBean.getQueryString() != null && !"".equals(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        List<Article> articleList =  articleDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<Article>(articleList);
    }
}
