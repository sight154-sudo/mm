package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author: tang
 * @date: Create in 15:21 2021/7/1
 * @description:
 */
public interface UserService {
    /**
     * 保存企业
     * @param user
     */
    void save(User user);

    /**
     * 删除企业
     * @param user
     */
    void delete(User user);

    void delete(String ids);
    /**
     * 修改企业
     * @param user
     */
    void update(User user);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    User findUserById(String id);
    /**
     * 查询所有
     * @return
     */
    List<User> findAllUser();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<User> findAllUser(Integer currentPage, Integer pageSize);

    List<Map<String,String>> findAllRole(String id);

    List<String> findRoleIdsByUserId(String id);

    void bindRoleByUserId(String uid, String ids);

    User login(String email, String password);

    List<String> findAllPermission(String id);
}
