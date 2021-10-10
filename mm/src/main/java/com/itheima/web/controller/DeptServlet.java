package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Dept;
import com.itheima.domain.Dept;
import com.itheima.domain.Result;
import com.itheima.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: tang
 * @date: Create in 14:21 2021/7/1
 * @description:
 */
@WebServlet("/system/dept/*")
public class DeptServlet extends BaseServlet {
    protected Result queryPage(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
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
            PageInfo<Dept> list = deptService.findAllDept(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true,MessageConstant.QUERY_DEPT_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_DEPT_SUCCESS);
        }
    }


    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            deptService.delete(ids);
            return new Result(true,MessageConstant.DELETE_DEPT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_DEPT_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Dept dept = getData(req,Dept.class);
            deptService.update(dept);
            return new Result(true,MessageConstant.EDIT_DEPT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_DEPT_FAIL);
        }

    }



    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Dept dept = deptService.findDeptById(id);
            return new Result(true,MessageConstant.QUERY_DEPT_SUCCESS,dept);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_DEPT_SUCCESS);
        }

    }

    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Dept dept = getData(req,Dept.class);
            deptService.save(dept);
            return new Result(true,MessageConstant.SAVE_DEPT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SAVE_DEPT_FAIL);
        }
    }

    protected Result findAllDept(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            List<Dept> list = null;
            if(StringUtils.isNotBlank(id)){
                list = deptService.findAllDept(id);
            }else{
                list = deptService.findAllDept();
            }
            return new Result(true,MessageConstant.QUERY_DEPT_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_DEPT_FAIL);
        }
    }

}
