package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 21:40 2021/7/1
 * @description:
 */
public interface RoleDao {
    Role findById(String did);
    List<Role> findAll();

    int save(Role role);

    int update(Role role);

    int delete(String did);

    void addModuleByRoleId(@Param("roleId") String roleId, @Param("moduleId") String mid);

    void deleteByRoleId(String roleId);
}
