package com.storyxc.mapper;

import com.storyxc.pojo.Image;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/14 18:35
 */
public interface ImageDao {
    void saveImage(Image image);

    List<Image> findPage(String queryString);

    Image getImageByName(String fullName);

    Image getImageByDate(String flag);

    void updateDownloadCount(String flag);

    void updateLikeCount(String date);

    Integer getLikeCountByDate(String date);

    Integer getDownloadCount(String flag);
}
