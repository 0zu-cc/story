package com.storyxc.service.impl;

import com.storyxc.controller.FileController;
import com.storyxc.service.FileService;
import com.storyxc.util.QiNiuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/9 13:27
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private QiNiuUtil qiNiuUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String uploadFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName;
        if (originalFileName.length() < 25) {
            String imgName = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = "storyxc/" + imgName + extName;
        }else{
            fileName = originalFileName;
        }
        try {
            qiNiuUtil.uploadViaByte(file.getBytes(), fileName);
            redisTemplate.boundSetOps("fileUpload").add(fileName);
        } catch (Exception e) {
            logger.warn("warn---------->>>>>>>>>>>>>>>:", e);
        }
        return "http://" + qiNiuUtil.domain + "/" + fileName;
    }
}
