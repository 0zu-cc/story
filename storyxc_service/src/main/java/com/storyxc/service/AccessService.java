package com.storyxc.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/19 11:35
 */
public interface AccessService {

    void updateVisitCount();

    Integer getVisitorCount();

    Integer getVisitCount();

    Map visitWebSite(HttpServletRequest request);
}
