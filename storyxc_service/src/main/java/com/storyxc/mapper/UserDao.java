package com.storyxc.mapper;

import com.storyxc.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:31
 */
public interface UserDao {
    void addUser(User user);

    void setUserRole(@Param("userId")Integer userId,@Param("roleId") Integer roleId);

    User findByUsername(String username);

    List<User> queryUserList(String queryString);

    User queryUserById(Integer id);

    List<Integer> getRoleIdsByUserId(Integer id);

    void delete(Integer id);

    void deleteUserRole(Integer id);

    void editUser(User user);
}
