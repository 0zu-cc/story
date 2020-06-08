package com.storyxc.task;

import com.alibaba.fastjson.JSON;
import com.storyxc.util.QiNiuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xc
 * @description 清理图片定时任务
 * @createdTime 2020/1/10 13:13
 */
@Configuration
@EnableScheduling
public class ClearUselessPicTask {

    private Logger logger = LoggerFactory.getLogger(ClearUselessPicTask.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private QiNiuUtils qiNiuUtils;


    /**
     * 清理无用的文章图片
     * 执行时间: 每周日的凌晨2点
     */
    @Scheduled(cron = "0 0 2 ? * 1")
    public void clearUselessArticlePic() {
        try {

            logger.info("开始执行清理无用文章图片定时任务");
//            Set<String> articleIds = redisTemplate.boundHashOps("article_pic").keys();
//            Set<String> usefulPics = new HashSet<>();
//            for (String articleId : articleIds) {
//                String setString = (String) redisTemplate.boundHashOps("article_pic").get(articleId);
//                usefulPics.addAll(JSON.parseArray(setString,String.class));
//            }
//            redisTemplate.boundSetOps("tempUsefulPics").add(usefulPics.toArray());
//            Set tempUsefulPics = redisTemplate.boundSetOps("tempUsefulPics").members();
//            Set<String> diff = redisTemplate.boundSetOps("articlePicSet").diff("tempUsefulPics");
//            logger.info("无用图片共[{}]个,图片列表:[{}],", diff.size(), diff);
//            String[] pics2Delete = diff.toArray(new String[]{});
//            long count = 0;
//            if (diff.size() > 0) {
//                qiNiuUtils.removeFiles(pics2Delete);
//                count = redisTemplate.boundSetOps("articlePicSet").remove(diff.toArray());
//            }
//            redisTemplate.delete("tempUsefulPics");
//            logger.info("清理无用文章图片任务执行完毕,共清理无用文件[{}]个", count);
//            logger.info("删除临时缓存usefulPics-[{}]", tempUsefulPics);
        } catch (RedisSystemException e) {
            logger.warn("清理图片任务失败");
        }
    }

}
