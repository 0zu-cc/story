package com.storyxc.service.upload.impl;

import com.storyxc.service.upload.UploadStrategy;
import com.storyxc.util.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xc
 * @description 壁纸上传策略
 * @createdTime 2020/1/10 9:48
 */
@Component
public class WallPaperUpload implements UploadStrategy {

    @Autowired
    private QiNiuUtils qiNiuUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = qiNiuUtils.uploadFile(file);
        String imgPath = "http://" + qiNiuUtils.domain + "/" + fileName;
        redisTemplate.boundSetOps("wallPaperSet").add(fileName);
        return imgPath;
    }
}
