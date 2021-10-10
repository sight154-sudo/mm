package com.itheima.dao;

import com.itheima.domain.Dept;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 14:15 2021/7/1
 * @description:
 */
public interface DeptDao {

    Dept findById(String did);
    List<Dept> findAll();

    int save(Dept dept);

    int update(Dept dept);

    int delete(String did);

    List<Dept> findAllExcept(String id);
}
