package com.storyxc.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.storyxc.entity.HttpClient;
import com.storyxc.pojo.Image;
import com.storyxc.service.ImageService;
import com.storyxc.util.FileDownloadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/14 17:18
 */
@Configuration
@EnableScheduling
public class GetWallPaperFromBingTask {

    private Logger logger = LoggerFactory.getLogger(GetWallPaperFromBingTask.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private FileDownloadUtils fileDownloadUtils;

    @Autowired
    private ImageService imageService;

    private final String PIC_INTERFACE = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";

    private final String BING_PREFIX = "http://cn.bing.com";

    private final String DIR_PATH = "/story/";
    /**
     * 获取每日Bing壁纸
     * 执行时间: 每天凌晨1点
     */
    @Scheduled(cron = "0 0 1 ? * *")
    public void getBingWallPaper() {
        logger.info("开始执行同步壁纸任务");
        HttpClient httpClient = new HttpClient(PIC_INTERFACE);
        try {
            httpClient.get();
            String response = httpClient.getContent();
            Image image = getImageFromResponse(response);
            //图片信息入库存储
            imageService.saveImage(image);
            String fileName = image.getUrl().substring("/th?id=OHR.".length(),image.getUrl().indexOf(".jpg")+4);
            //壁纸文件下载到本地
            fileDownloadUtils.download(BING_PREFIX+image.getUrl(),DIR_PATH, fileName, "wallPaper");
            logger.info("下载文件成功,文件名[{}],文件路径[{}]",fileName, DIR_PATH+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Image getImageFromResponse(String response) {
        Image image = null;
        try {
            JSONObject jsonObject = JSON.parseObject(response);
            List imageList = (List) jsonObject.get("images");
            Object imageObj = imageList.get(0);
            String imageJson = JSON.toJSONString(imageObj);
            image = JSON.parseObject(imageJson, Image.class);
        } catch (Exception e) {
            logger.error("bing壁纸响应解析错误:[{}]", e);
        }
        return image;
    }
}
