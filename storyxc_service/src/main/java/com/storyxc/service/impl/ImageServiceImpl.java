package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.ImageDao;
import com.storyxc.pojo.Image;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ImageService;
import com.storyxc.util.PageHelperUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/14 18:35
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public void saveImage(Image image) {
        String imageDate = image.getEnddate();
        String date = imageDate.substring(0,4)+"-"+(imageDate.substring(4,6))+"-"+(imageDate.substring(6,8));
        image.setDate(date);
        String fullName = image.getUrl().substring("/th?id=OHR.".length(),image.getUrl().indexOf(".jpg")+4);
        image.setFullName(fullName);
        image.setFullUrl("http://io.storyxc.com/images/"+fullName);
        imageDao.saveImage(image);
    }

    @Override
    public PageInfo<Image> findPage(QueryPageBean queryPageBean) {
        PageHelperUtils.startPage(queryPageBean);
        List<Image> imageList = imageDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<>(imageList);
    }

    @Override
    public Image getImageByName(String fullName) {
        return imageDao.getImageByName(fullName);
    }

    @Override
    public Image getImageByDate(String flag) {
        return imageDao.getImageByDate(flag);
    }

    @Override
    public void updateDownloadCount(String flag) {
        imageDao.updateDownloadCount(flag);
    }

    @Override
    public Integer updateLikeCount(String date, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(),"like_"+date)){
                return null;
            }
        }
        Cookie cookie = new Cookie("like_"+date,"1");
        cookie.setMaxAge(600);
        cookie.setPath("/");
        response.addCookie(cookie);
        imageDao.updateLikeCount(date);
        return imageDao.getLikeCountByDate(date);
    }

    @Override
    public Integer getDownloadCount(String flag) {
        return imageDao.getDownloadCount(flag);
    }

    @Override
    public Image getTodayPic() {
        return imageDao.getTodayPic();
    }
}
