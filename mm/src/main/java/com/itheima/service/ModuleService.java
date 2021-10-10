package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Module;

import java.util.List;
import java.util.Map;

/**
 * @author: tang
 * @date: Create in 22:03 2021/7/1
 * @description:
 */
public interface ModuleService {
    void save(Module module);

    void delete(Module module);

    void delete(String ids);
    void update(Module module);

    Module findModuleById(String id);
    List<Module> findAllModule();

    PageInfo<Module> findAllModule(Integer currentPage, Integer pageSize);

    List<Module> findAllModule(String id);

    List<Map<String, String>> findAuthorDataByRoleId(String id);

    List<Module> findAllModuleAndLevel(String uid);

    /**
     * 根据用户id查询可访问的路径
     * @param id
     * @return
     */
    List<String> findPermissionByUserId(String id);
}
