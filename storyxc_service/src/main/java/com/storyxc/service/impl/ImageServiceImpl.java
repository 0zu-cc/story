package com.storyxc.service.impl;

import com.storyxc.mapper.ImageDao;
import com.storyxc.pojo.Image;
import com.storyxc.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        imageDao.saveImage(image);
    }
}
