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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/18 15:39
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
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
            String articleMain = article.getArticleMain();
            String regex = "http://io\\.storyxc.com/storyxc/\\w+(\\.png|\\.jpg|\\.jpeg|\\.gif)";
            Set<String> picSet = new HashSet<>();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(articleMain);
            Map picMap = new HashMap<Integer,String>();
            int i = 1;
            while (matcher.find()) {
                picMap.put(i,matcher.group());
                i++;
            }
            redisTemplate.boundHashOps("articlePic_"+article.getId()).putAll(picMap);

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

    @Override
    @Transactional
    public void deleteArticle(Integer id) {
        articleDao.deleteArticle(id);
        articleDao.deleteArticleTag(id);
        articleDao.deleteArticleCategory(id);
        redisTemplate.delete("articlePic_"+id);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public void editArticleInfo(Article article) {
        articleDao.editArticleInfo(article);
    }

    @Override
    public PageInfo<Article> findPageManage(QueryPageBean queryPageBean) {
        PageHelperUtil.startPage(queryPageBean);
        List<Article> articleList = articleDao.findPageManage(queryPageBean.getQueryString());
        return new PageInfo<Article>(articleList);    }

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
