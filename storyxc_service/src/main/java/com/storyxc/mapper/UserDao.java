package com.storyxc.mapper;

import com.storyxc.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:31
 */
public interface UserDao {
    void addUser(User user);

    void setUserRole(@Param("userId")Integer userId,@Param("roleId") Integer roleId);

    User findByUsername(String username);
}
