package com.storyxc.service;

import com.github.pagehelper.PageInfo;
import com.storyxc.pojo.Image;
import com.storyxc.pojo.QueryPageBean;

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

    Integer updateLikeCount(String date);

    Integer getDownloadCount(String flag);

    Image getTodayPic();
}
