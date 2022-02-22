package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.Image;
import com.storyxc.pojo.QueryPageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Xc
 * @description
 * @createdTime 2020/1/14 18:34
 */
public interface ImageService {
    void saveImage(Image image);

    PageInfo<Image> findPage(QueryPageBean queryPageBean);

    Image getImageByName(String fullName);

    Image getImageByDate(String flag);

    void updateDownloadCount(String flag);

    Integer updateLikeCount(String date, HttpServletRequest request, HttpServletResponse response);

    Integer getDownloadCount(String flag);

    Image getTodayPic();

    void pull() throws Exception;
}
