package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Dept;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 14:22 2021/7/1
 * @description:
 */
public interface DeptService {
    /**
     * 保存企业
     * @param dept
     */
    void save(Dept dept);

    /**
     * 删除企业
     * @param dept
     */
    void delete(Dept dept);

    void delete(String ids);
    /**
     * 修改企业
     * @param dept
     */
    void update(Dept dept);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    Dept findDeptById(String id);
    /**
     * 查询所有
     * @return
     */
    List<Dept> findAllDept();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Dept> findAllDept(Integer currentPage, Integer pageSize);

    List<Dept> findAllDept(String id);
}
