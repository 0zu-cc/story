package com.storyxc.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/9 13:27
 */
public interface FileService {
    String uploadFile(MultipartFile file);
}
