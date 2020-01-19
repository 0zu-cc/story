package com.storyxc.service.impl;

import com.storyxc.mapper.AccessDao;
import com.storyxc.service.AccessService;
import com.storyxc.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public void addVisitorInfo(String remoteAddr, String visitorLocation) {
        String date = DateUtils.parseDateToString(new Date());
        try {
            Integer count = accessDao.findByIp(remoteAddr);
            if (count == 0) {
                accessDao.addVisitorInfo(remoteAddr, visitorLocation, date);
            } else {
                accessDao.updateVisitorInfo(remoteAddr, visitorLocation, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVisitCount() {
        accessDao.updateVisitCount();
    }

    @Override
    public void addVisitorStatistics(String remoteAddr, String visitorLocation) {
        accessDao.addVisitorStatistics(remoteAddr,visitorLocation,DateUtils.parseDateToString(new Date()));
    }
}
