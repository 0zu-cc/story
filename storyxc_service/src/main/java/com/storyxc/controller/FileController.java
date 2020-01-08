package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.util.QiNiuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/8 18:44
 */
@RestController
@RequestMapping("/story/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private QiNiuUtil qiNiuUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public Result uploadFile(@RequestBody MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        try{
            qiNiuUtil.uploadViaByte(file.getBytes(),originalFilename);
            //TODO 文件名存入redis

        }catch (Exception e){
            logger.warn("warn---------->>>>>>>>>>>>>>>:",e);
        }
        String imgPath = "http://"+qiNiuUtil.domain+"/"+originalFilename;
        return new Result(true, StatusCode.OK,"上传文件成功",imgPath);
    }
}
