package com.storyxc.service.impl;

import com.storyxc.mapper.AccessDao;
import com.storyxc.service.AccessService;
import com.storyxc.util.AccessStatisticsUtils;
import com.storyxc.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/19 11:35
 */
@Service
public class AccessServiceImpl implements AccessService {
    private static final Logger logger = LoggerFactory.getLogger(AccessServiceImpl.class);

    @Autowired
    private AccessDao accessDao;


    @Override
    public void updateVisitCount() {
        accessDao.updateVisitCount();
    }


    @Override
    public Integer getVisitorCount() {
        return accessDao.getVisitorCount();
    }

    @Override
    public Integer getVisitCount() {
        return accessDao.getVisitCount();
    }

    @Override
    public Map visitWebSite(HttpServletRequest request) {
        updateVisitCount();
        Map<String, String> map = new HashMap<String, String>();
        String remoteAddr = getRealIp(request);
        logger.info("ip:[{}]的用户访问了网站", remoteAddr);
        Integer count = accessDao.findByIp(remoteAddr);
        String visitorLocation = AccessStatisticsUtils.getVistorLocation(remoteAddr);
        //本地测试用
        //String remoteAddr = "127.0.0.1";
        //String visitorLocation = "中国-广东-深圳";
        if (count == 0) {
            accessDao.addVisitorInfo(remoteAddr, visitorLocation, DateUtils.parseDateToString(new Date()));
        } else {
            accessDao.updateVisitorInfo(remoteAddr, visitorLocation, DateUtils.parseDateToString(new Date()));
        }
        accessDao.addVisitorStatistics(remoteAddr, visitorLocation, DateUtils.parseDateToString(new Date()));
        Integer visitCount = getVisitCount();
        Integer visitorCount = getVisitorCount();
        map.put("ip", remoteAddr);
        map.put("location", visitorLocation);
        map.put("visitorCount", String.valueOf(visitorCount));
        map.put("visitCount", String.valueOf(visitCount));
        return map;
    }

    private String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

