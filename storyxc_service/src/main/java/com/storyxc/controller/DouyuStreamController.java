package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Image;
import com.storyxc.service.DouyuStreamService;
import com.storyxc.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xc
 * @description
 * @createdTime 2020/2/9 16:07
 */
@RestController
@RequestMapping("/story/liveStream")
public class DouyuStreamController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private DouyuStreamService douyuStreamService;

    @GetMapping
    public Result getDouyuRealStreamURL(String roomId){
        String realLiveStreamURL = douyuStreamService.getRealLiveStreamURL(roomId);
        return new Result(true, StatusCode.OK,"解析完毕",realLiveStreamURL);
    }

    @GetMapping("/pic")
    public Result getTodayPic(){
        Image image = imageService.getTodayPic();
        return new Result(true, StatusCode.OK,"查询今日壁纸成功",image.getFullUrl());
    }
}
