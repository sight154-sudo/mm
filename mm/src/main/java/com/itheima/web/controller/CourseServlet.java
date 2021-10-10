package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Course;
import com.itheima.domain.Dept;
import com.itheima.domain.Result;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: tang
 * @date: Create in 17:42 2021/7/1
 * @description:
 */
@WebServlet("/store/course/*")
public class CourseServlet extends BaseServlet{
    protected Result queryPage(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        try {
            String currentPageStr = req.getParameter("currentPage");
            String pageSizeStr = req.getParameter("pageSize");
            Integer currentPage,pageSize;
            currentPage = Integer.valueOf(currentPageStr);
            pageSize = Integer.valueOf(pageSizeStr);
            if(StringUtils.isBlank(currentPageStr)){
                currentPage = 1;
            }
            if(StringUtils.isBlank(pageSizeStr)){
                pageSize = 5;
            }
            PageInfo<Course> list = courseService.findAllCourse(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true,MessageConstant.QUERY_COURSE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_COURSE_SUCCESS);
        }
    }


    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            courseService.delete(ids);
            return new Result(true,MessageConstant.DELETE_COURSE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_COURSE_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Course course = getData(req,Course.class);
            courseService.update(course);
            return new Result(true,MessageConstant.EDIT_COURSE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_COURSE_FAIL);
        }

    }



    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Course course = courseService.findCourseById(id);
            return new Result(true,MessageConstant.QUERY_COURSE_SUCCESS,course);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_COURSE_SUCCESS);
        }

    }

    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Course course = getData(req,Course.class);
            courseService.save(course);
            return new Result(true,MessageConstant.SAVE_COURSE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SAVE_COURSE_FAIL);
        }
    }

    /**
     * 查询所有课程
     * @param req
     * @param resp
     * @return
     */
    protected Result findAllCourse(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Course> list = courseService.findAllCourse();
            return new Result(true,MessageConstant.QUERY_DEPT_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_DEPT_FAIL);
        }
    }
}
