package com.itheima.dao;

import com.itheima.domain.Module;

import java.util.List;
import java.util.Map;

/**
 * @author: tang
 * @date: Create in 22:01 2021/7/1
 * @description:
 */
public interface ModuleDao {
    Module findById(String did);
    List<Module> findAll();
    List<Module> findAllByUid(String uid);
    int save(Module module);

    int update(Module module);

    int delete(String did);

    /**
     * 查询所有模块信息除去自己与父模块为自己的模块
     * @param id
     * @return
     */
    List<Module> findAllExcept(String id);


    List<Map<String, String>> findAuthorDataByRoleId(String roleId);

}
