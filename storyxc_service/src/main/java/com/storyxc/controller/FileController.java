package com.storyxc.controller;

import com.storyxc.entity.Result;
import com.storyxc.entity.StatusCode;
import com.storyxc.service.download.DownloadStrategyContext;
import com.storyxc.service.upload.UploadStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/8 18:44
 */
@RestController
@RequestMapping("/story/file")
public class FileController {

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private DownloadStrategyContext downloadStrategyContext;

    /**
     * 文件上传
     * @param file
     * @param type 上传文件类型
     * @return
     */
    @PostMapping
    public Result uploadFile(@RequestBody MultipartFile file,String type) {
        String imgPath = uploadStrategyContext.uploadFile(file,type);
        return new Result(true, StatusCode.OK, "上传文件成功", imgPath);
    }

    /**
     * 文件下载
     * @param flag 下载文件的标识,不同类型文件标识字段不同
     * @param type 下文件的类型
     * @param response
     */
    @GetMapping("/download")
    public void downloadFile(String flag, String type, HttpServletResponse response){
        downloadStrategyContext.downloadFile(flag,type,response);
    }
}
