package com.storyxc.service.download;

import com.storyxc.entity.DownloadStrategyEntity;
import com.storyxc.mapper.DownloadStrategyDao;
import com.storyxc.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/17 13:42
 */
@Component
public class DownloadStrategyContext {
    @Autowired
    private DownloadStrategyDao downloadStrategyDao;

    public void downloadFile(String flag, String type, HttpServletResponse response) {
        //根据type查出beanName
        DownloadStrategyEntity downloadStrategy = downloadStrategyDao.getBeanName(type);
        String beanName = downloadStrategy.getBeanName();
        //调用不同的策略进行下载
        SpringUtils.getBean(beanName, DownloadStrategy.class).downloadFile(flag, response);
    }
}
