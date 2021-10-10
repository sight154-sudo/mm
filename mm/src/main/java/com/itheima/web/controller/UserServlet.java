package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.User;
import com.itheima.domain.Result;
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
 * @date: Create in 15:22 2021/7/1
 * @description:
 */
@WebServlet("/system/user/*")
public class UserServlet extends BaseServlet{
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
            PageInfo<User> list = userService.findAllUser(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true,MessageConstant.QUERY_USER_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_USER_SUCCESS);
        }
    }

    /**
     * 查询用户所有的权限
     * @param req
     * @param resp
     * @return
     */
    protected Result findAllPermission(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            List<String> list = userService.findAllPermission(user.getId());
            return new Result(true,MessageConstant.QUERY_PERMISSIONLIST_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_PERMISSIONLIST_FAILE);
        }
    }



    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            userService.delete(ids);
            return new Result(true,MessageConstant.DELETE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_USER_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = getData(req,User.class);
            userService.update(user);
            return new Result(true,MessageConstant.EDIT_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_USER_FAIL);
        }

    }

    /**
     * 查询所有的角色
     * @param req
     * @param resp
     * @return
     */
    protected Result findAllRole(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            List<Map<String,String>> list = userService.findAllRole(id);
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    /**
     * 查询与用户关联的角色
     * @param req
     * @param resp
     * @return
     */
    protected Result findRoleIdsByUserId(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            List<String> checkIds = userService.findRoleIdsByUserId(id);
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,checkIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    /**
     * 给用户绑定角色
     * @param req
     * @param resp
     * @return
     */
    protected Result bindRoleByUserId(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String uid = req.getParameter("id");
            String ids = req.getParameter("ids");
            userService.bindRoleByUserId(uid,ids);
            return new Result(true,MessageConstant.SETTING_ROLE_AUTHORIZE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SETTING_ROLE_AUTHORIZE_FAIL);
        }
    }


    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            User user = userService.findUserById(id);
            return new Result(true,MessageConstant.QUERY_USER_SUCCESS,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_USER_SUCCESS);
        }

    }

    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = getData(req,User.class);
            userService.save(user);
            return new Result(true,MessageConstant.SAVE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SAVE_USER_FAIL);
        }
    }
}
