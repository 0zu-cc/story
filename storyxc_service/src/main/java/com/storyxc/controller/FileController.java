package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.service.FileService;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private FileService fileService;

    @PostMapping
    public Result uploadFile(@RequestBody MultipartFile file) {
        String imgPath = fileService.uploadFile(file);
        return new Result(true, StatusCode.OK, "上传文件成功", imgPath);
    }
}
