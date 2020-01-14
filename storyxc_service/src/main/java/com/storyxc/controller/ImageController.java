package com.storyxc.controller;

import com.storyxc.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/14 18:34
 */
@RestController
@RequestMapping("/story/image")
public class ImageController {

    @Autowired
    private ImageService imageService;
}
