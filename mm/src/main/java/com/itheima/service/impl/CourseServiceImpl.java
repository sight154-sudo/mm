package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CourseDao;
import com.itheima.domain.Course;
import com.itheima.service.CourseService;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 17:44 2021/7/1
 * @description:
 */
public class CourseServiceImpl implements CourseService {
    @Override
    public void save(Course course) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            //给企业设置id
            String id = UUID.randomUUID().toString();
            course.setId(id);
            course.setCreateTime(new Date());
            courseDao.save(course);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(Course course) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            courseDao.delete(course.getId());
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String ids) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            String[] strs = ids.split(",");
            Arrays.stream(strs).forEach(id->courseDao.delete(id));
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Course course) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            courseDao.update(course);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Course findCourseById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Course> findAllCourse() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            return courseDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo<Course> findAllCourse(Integer currentPage, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            PageHelper.startPage(currentPage,pageSize);
            List<Course> list = courseDao.findAll();
            PageInfo<Course> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
