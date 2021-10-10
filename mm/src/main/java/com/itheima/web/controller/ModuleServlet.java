package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Result;
import com.itheima.domain.Module;
import com.itheima.domain.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: tang
 * @date: Create in 22:06 2021/7/1
 * @description:
 */
@WebServlet("/system/module/*")
public class ModuleServlet extends BaseServlet{
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
            PageInfo<Module> list = moduleService.findAllModule(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_SUCCESS);
        }
    }


    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            moduleService.delete(ids);
            return new Result(true,MessageConstant.DELETE_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_ROLE_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Module module = getData(req,Module.class);
            moduleService.update(module);
            return new Result(true,MessageConstant.EDIT_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_ROLE_FAIL);
        }

    }

    /**
     * 查询所有的模块除去自身与和parentId是自己的模块
     * @param req
     * @param resp
     * @return
     */
    protected Result findAllExcept(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            List<Module> list = moduleService.findAllModule(id);
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_SUCCESS);
        }

    }


    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Module module = moduleService.findModuleById(id);
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,module);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_SUCCESS);
        }

    }

    /**
     * 查询所有的模块信息保存层级关系
     * @param req
     * @param resp
     * @return
     */
    protected Result findMenu(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            List<Module> list = moduleService.findAllModuleAndLevel(user.getId());
            return new Result(true,MessageConstant.QUERY_MODULE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_MODULE_FAIL);
        }
    }

    protected Result findAll(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Module> list = moduleService.findAllModule();
            return new Result(true,MessageConstant.QUERY_DEPT_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_DEPT_FAIL);
        }
    }
    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Module module = getData(req,Module.class);
            moduleService.save(module);
            return new Result(true,MessageConstant.SAVE_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SAVE_ROLE_FAIL);
        }
    }
}
