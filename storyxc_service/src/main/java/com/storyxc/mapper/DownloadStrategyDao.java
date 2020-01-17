package com.storyxc.mapper;

import com.storyxc.entity.DownloadStrategyEntity;
import org.apache.ibatis.annotations.Select;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/17 13:51
 */
public interface DownloadStrategyDao {
    @Select("select * from story_download_strategy where type = #{type}")
    DownloadStrategyEntity getBeanName(String type);
}
