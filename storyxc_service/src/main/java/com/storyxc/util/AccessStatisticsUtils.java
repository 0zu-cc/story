package com.storyxc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/19 13:22
 */
public class AccessStatisticsUtils {
    private static final Logger logger = LoggerFactory.getLogger(AccessStatisticsUtils.class);
    public static final String IP_LOCATION_INTERFACE = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    public static String getVistorLocation(String ipAddr) {
        String location = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            String response = HttpClientUtils.getResponseStr(httpClient,IP_LOCATION_INTERFACE + ipAddr);
            Map map = JSON.parseObject(response, Map.class);
            Map data = (Map) map.get("data");
            String country = (String) data.get("country");
            String region = (String) data.get("region");
            String city = (String) data.get("city");
            location = country + "-" + region + "-" + city;
            return location;
        } catch (IOException e) {
            logger.warn("IO异常");
        }catch (JSONException e){
            logger.warn("ip解析异常");
        }
        return location;
    }
}
