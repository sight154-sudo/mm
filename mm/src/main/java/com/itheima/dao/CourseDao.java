package com.itheima.dao;

import com.itheima.domain.Course;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 16:42 2021/7/1
 * @description:
 */
public interface CourseDao {
    Course findById(String did);
    List<Course> findAll();

    int save(Course course);

    int update(Course course);

    int delete(String did);
}
