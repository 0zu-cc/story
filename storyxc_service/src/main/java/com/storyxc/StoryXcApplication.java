package com.storyxc;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/11 15:24
 */
@SpringBootApplication
@MapperScan("com.storyxc.mapper")
public class StoryXcApplication {
    private static final Logger logger = LoggerFactory.getLogger(StoryXcApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(StoryXcApplication.class,args);
        logger.info("启动成功");
    }
}
