package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 21:41 2021/7/1
 * @description:
 */
public interface RoleService {
    void save(Role role);

    void delete(Role role);

    void delete(String ids);
    void update(Role role);

    Role findRoleById(String id);
    List<Role> findAllRole();

    PageInfo<Role> findAllRole(Integer currentPage, Integer pageSize);

    void addModuleByRoleId(String roleId, String ids);
}
