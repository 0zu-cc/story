package com.storyxc.service.download.impl;

import com.storyxc.pojo.Image;
import com.storyxc.service.ImageService;
import com.storyxc.service.download.DownloadStrategy;
import com.storyxc.util.HttpClientUtils;
import com.storyxc.util.QiNiuUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Xc
 * @description 壁纸下载策略
 * @createdTime 2020/1/17 13:55
 */
@Component
public class ImageDownload implements DownloadStrategy {

    @Autowired
    private ImageService imageService;

    @Autowired
    private QiNiuUtils qiNiuUtils;

    /**
     * 壁纸下载
     *
     * @param flag     壁纸的日期
     * @param response
     */
    @Override
    public void downloadFile(String flag, HttpServletResponse response) {
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        //根据日期查找在云服务器上的文件名
        Image image = imageService.getImageByDate(flag);
        String fileName = "images/" + image.getFullName();
        //下载文件
        String url = "http://" + qiNiuUtils.domain + "/" + fileName;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            inputStream = HttpClientUtils.getResponseInputStream(httpClient,url);
            outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            response.setHeader("content-Disposition", "attachment;filename=" + image.getFullName());
            if (inputStream != null) {
                IOUtils.copy(inputStream, outputStream);
            }
            outputStream.flush();
            //更新数据库文件下载数
            imageService.updateDownloadCount(flag);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
