package com.storyxc.service.impl;

import com.storyxc.mapper.TagDao;
import com.storyxc.pojo.Tag;
import com.storyxc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 11:07
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> queryAllTags() {
        return tagDao.queryAllTags();
    }
}
