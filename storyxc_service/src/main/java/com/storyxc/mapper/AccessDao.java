package com.storyxc.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/19 11:36
 */
public interface AccessDao {
    Integer addVisitorInfo(@Param("remoteAddr") String remoteAddr,
                           @Param("visitorLocation") String visitorLocation,
                           @Param("loginTime") String loginTime);

    Integer findByIp(String remoteAddr);

    void updateVisitorInfo(@Param("remoteAddr") String remoteAddr,
                           @Param("visitorLocation") String visitorLocation,
                           @Param("loginTime") String loginTime);

    void updateVisitCount();

    void addVisitorStatistics(@Param("remoteAddr") String remoteAddr,
                              @Param("visitorLocation") String visitorLocation,
                              @Param("loginTime") String loginTime);
}
