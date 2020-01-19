package com.storyxc.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/19 13:22
 */
public class AccessStatisticsUtils {

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
            e.printStackTrace();
        }
        return location;
    }
}
