package com.storyxc.service.download;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/17 13:41
 */
public interface DownloadStrategy {
    void downloadFile(String flag, HttpServletResponse response);
}
