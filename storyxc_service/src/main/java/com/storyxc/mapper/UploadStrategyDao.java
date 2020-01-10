package com.storyxc.mapper;

import com.storyxc.pojo.UploadStrategyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/10 9:57
 */
@Mapper
@Repository
public interface UploadStrategyDao {

    @Select("select * from story_upload_strategy where type = #{type}")
    UploadStrategyEntity getBeanName(String type);
}
