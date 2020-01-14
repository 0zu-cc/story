package com.storyxc.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
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

        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                //如果是同步的壁纸 上传到七牛云
                if ("wallPaper".equals(type)) {
                    qiNiuUtils.uploadViaByte(inputStreamToByteArray(inputStream),"/story/"+fileName);
                }
                saveFileToDisk(inputStream, dirPath, fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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