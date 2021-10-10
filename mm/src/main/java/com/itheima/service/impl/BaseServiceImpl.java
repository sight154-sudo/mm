package com.itheima.service.impl;

import com.github.pagehelper.PageInfo;
import com.itheima.dao.CourseDao;
import com.itheima.domain.Course;
import com.itheima.service.BaseService;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 16:48 2021/7/1
 * @description:
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public void save(T t) {

    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void delete(String ids) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public T findTById(String id) {
        return null;
    }

    @Override
    public List<T> findAllT() {
        return null;
    }

    @Override
    public PageInfo<T> findAllT(Integer currentPage, Integer pageSize) {
        return null;
    }
}
