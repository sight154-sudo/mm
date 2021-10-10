package com.itheima.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.Result;
import com.itheima.factory.BeanFactory;
import com.itheima.service.*;
import com.itheima.service.impl.CompanyServiceImpl;
import com.itheima.service.impl.DeptServiceImpl;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: tang
 * @date: Create in 9:21 2021/7/1
 * @description:
 */
public class BaseServlet extends HttpServlet {


    protected ObjectMapper objectMapper;
    protected CompanyService companyService;
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;
    protected QuestionService questionService;
    protected QuestionItemService questionItemService;
    protected RoleService roleService;
    protected ModuleService moduleService;

    @Override
    public void init() throws ServletException {
        objectMapper = new ObjectMapper();
        companyService = BeanFactory.getBean("companyService",CompanyService.class);
        deptService = BeanFactory.getBean("deptService",DeptService.class);
        userService = BeanFactory.getBean("userService",UserService.class);
        courseService = BeanFactory.getBean("courseService",CourseService.class);
        catalogService = BeanFactory.getBean("catalogService",CatalogService.class);
        questionService = BeanFactory.getBean("questionService",QuestionService.class);
        questionItemService = BeanFactory.getBean("questionItemService",QuestionItemService.class);
        roleService = BeanFactory.getBean("roleService",RoleService.class);
        moduleService = BeanFactory.getBean("moduleService",ModuleService.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uri = req.getPathInfo().substring(1);

            Class clazz = this.getClass();
            //获取方法对象
            Method method = clazz.getDeclaredMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.setAccessible(true);
            Result result = (Result) method.invoke(this, req, resp);
            this.returnRes(resp,result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected  <T> T getData(HttpServletRequest req, Class<T> clazz) {
        return BeanUtil.fillBeanFromJson(req, clazz);
    }
    protected void returnRes(HttpServletResponse resp, Result result) throws IOException {
        objectMapper.writeValue(resp.getOutputStream(),result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
