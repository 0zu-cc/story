package com.storyxc.service;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/19 11:35
 */
public interface AccessService {
    void addVisitorInfo(String remoteAddr, String visitorLocation);

    void updateVisitCount();

    void addVisitorStatistics(String remoteAddr, String visitorLocation);
}
