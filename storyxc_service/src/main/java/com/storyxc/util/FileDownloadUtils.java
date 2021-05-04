package com.storyxc.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileDownloadUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileDownloadUtils.class);


    @Autowired
    private QiNiuUtils qiNiuUtils;

    /**
     * @param url      文件url地址
     * @param dirPath  目录路径
     * @param fileName 文件路径
     */
    public void download(String url, String dirPath, String fileName, String type) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            InputStream inputStream = HttpClientUtils.getResponseInputStream(httpClient,url);
            //将输入流转换为字节数组
            byte[] byteArray = inputStreamToByteArray(inputStream);
            assert byteArray != null;
            InputStream reuseInputStream = new ByteArrayInputStream(byteArray);
            //如果是同步的壁纸 上传到七牛云
            if ("wallPaper".equals(type)) {
                String tempFileName = "images/" + fileName;
                qiNiuUtils.uploadViaByte(byteArray, tempFileName);
            }
            // 20210505 壁纸不再保存到服务器
            //saveFileToDisk(reuseInputStream, dirPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将文件写到硬盘指定目录下
     *
     * @param in       输入流
     * @param dirPath  目录路径
     * @param filePath 文件路径
     */
    private static void saveFileToDisk(InputStream in, String dirPath, String filePath) {

        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                boolean mkdirs = dir.mkdirs();
            }

            //文件完整路径
            String fullPath = dirPath.concat(filePath);
            File file = new File(fullPath);
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输入流转换字节数组
     *
     * @param inputStream
     * @return
     */
    public static byte[] inputStreamToByteArray(InputStream inputStream) {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int len = 0;
            while (-1 != (len = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("输入流转字节数组出现错误", e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}