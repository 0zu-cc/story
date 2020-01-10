package com.storyxc.service.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xc
 * @description 图片上传策略接口
 * @createdTime 2020/1/10 9:44
 */
public interface UploadStrategy {
    String uploadFile(MultipartFile file);
}
