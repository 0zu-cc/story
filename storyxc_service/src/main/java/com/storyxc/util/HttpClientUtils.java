package com.storyxc.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

/*****
 * @Author: xc
 * @Description: HttpClient 工具类
 * @createdTime: 2020/1/19 14:02
 ****/
public class HttpClientUtils {

    /**
     * 获取响应输入流
     *
     * @param httpClient
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream getResponseInputStream(CloseableHttpClient httpClient, String url) throws IOException {
        HttpGet httpGet = buildHttpGet(url);
        InputStream inputStream = null;
        HttpResponse response = httpClient.execute(httpGet);
        if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            return inputStream;
        }
        return inputStream;
    }

    /**
     * 获取响应的字符串
     *
     * @param httpClient
     * @param url
     * @return
     * @throws IOException
     */
    public static String getResponseStr(CloseableHttpClient httpClient, String url) throws IOException {
        String responseStr = null;
        HttpGet httpGet = buildHttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();
            responseStr = EntityUtils.toString(entity);
            return responseStr;
        }
        return responseStr;
    }

    /**
     * 根据url构建httpGet对象
     *
     * @param url
     * @return
     */
    public static HttpGet buildHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        return httpGet;
    }
}