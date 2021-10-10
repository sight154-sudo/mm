package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Course;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 17:43 2021/7/1
 * @description:
 */
public interface CourseService{
    /**
     * 保存企业
     * @param course
     */
    void save(Course course);

    /**
     * 删除企业
     * @param course
     */
    void delete(Course course);

    void delete(String ids);
    /**
     * 修改企业
     * @param course
     */
    void update(Course course);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    Course findCourseById(String id);
    /**
     * 查询所有
     * @return
     */
    List<Course> findAllCourse();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Course> findAllCourse(Integer currentPage, Integer pageSize);
}
