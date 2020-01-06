package com.storyxc.service.impl;

import com.storyxc.mapper.MenuDao;
import com.storyxc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/6 15:51
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
}
