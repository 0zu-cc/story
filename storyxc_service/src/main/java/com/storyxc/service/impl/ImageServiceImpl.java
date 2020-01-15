package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.ImageDao;
import com.storyxc.pojo.Image;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ImageService;
import com.storyxc.util.PageHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        image.setViewFileUrl("https://www.storyxc.com/image/"+fullName);
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
}
