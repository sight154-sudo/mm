package com.itheima.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Result;
import com.itheima.domain.Role;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: tang
 * @date: Create in 21:43 2021/7/1
 * @description:
 */
@WebServlet("/system/role/*")
public class RoleServlet extends BaseServlet{
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
            PageInfo<Role> list = roleService.findAllRole(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_SUCCESS);
        }
    }

    protected Result author(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String id = req.getParameter("id");
            //根据用户id查询关联的模块信息
            List<Map<String,String>> list = moduleService.findAuthorDataByRoleId(id);
            return new Result(true,MessageConstant.LOAD_ZTREE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.LOAD_ZTREE_FAIL);
        }
    }
    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            roleService.delete(ids);
            return new Result(true,MessageConstant.DELETE_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_ROLE_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Role role = getData(req,Role.class);
            roleService.update(role);
            return new Result(true,MessageConstant.EDIT_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_ROLE_FAIL);
        }

    }



    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Role role = roleService.findRoleById(id);
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS,role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_SUCCESS);
        }

    }

    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Role role = getData(req,Role.class);
            roleService.save(role);
            return new Result(true,MessageConstant.SAVE_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SAVE_ROLE_FAIL);
        }
    }
    protected Result addModuleByRoleId(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String roleId = req.getParameter("roleId");
            String ids = req.getParameter("moduleIds");
            roleService.addModuleByRoleId(roleId,ids);
            return new Result(true,MessageConstant.SETTING_ROLE_AUTHORIZE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SETTING_ROLE_AUTHORIZE_FAIL);
        }
    }


}
