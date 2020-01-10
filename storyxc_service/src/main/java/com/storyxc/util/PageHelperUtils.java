package com.storyxc.util;

import com.github.pagehelper.PageHelper;
import com.storyxc.pojo.QueryPageBean;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/3 15:43
 */
public class PageHelperUtils {

    public static void startPage(QueryPageBean queryPageBean){
        if (queryPageBean.getQueryString() != null && !"".equals(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
    }
}
