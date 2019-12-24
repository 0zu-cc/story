package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.pojo.Tag;
import com.storyxc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:01
 */
@RestController
@RequestMapping("/story/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public Result queryAllTags(){
        List<Tag> tags = tagService.queryAllTags();
        return new Result(true, StatusCode.OK,"查询标签列表成功",tags);
    }
}
