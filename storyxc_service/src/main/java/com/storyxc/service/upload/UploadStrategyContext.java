package com.storyxc.service.upload;

import com.storyxc.mapper.UploadStrategyDao;
import com.storyxc.pojo.UploadStrategyEntity;
import com.storyxc.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xc
 * @description 图片文件上传策略上下文
 * @createdTime 2020/1/10 9:49
 */
@Component
public class UploadStrategyContext {

    @Autowired
    private UploadStrategyDao uploadStrategyDao;

    public String uploadFile(MultipartFile file,String type){
        //根据type查出beanName
        UploadStrategyEntity uploadStrategy = uploadStrategyDao.getBeanName(type);
        String beanName = uploadStrategy.getBeanName();
        //调用不同的策略进行上传
        return SpringUtils.getBean(beanName,UploadStrategy.class).uploadFile(file);
    }

}
