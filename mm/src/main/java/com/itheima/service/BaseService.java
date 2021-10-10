package com.itheima.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 16:44 2021/7/1
 * @description:
 */
public interface BaseService<T> {
    /**
     * 保存企业
     * @param t
     */
    void save(T t);

    /**
     * 删除企业
     * @param t
     */
    void delete(T t);

    void delete(String ids);
    /**
     * 修改企业
     * @param t
     */
    void update(T t);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    T findTById(String id);
    /**
     * 查询所有
     * @return
     */
    List<T> findAllT();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<T> findAllT(Integer currentPage, Integer pageSize);
}
