package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Company;
import com.itheima.domain.Course;
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
 * @date: Create in 9:20 2021/7/1
 * @description:
 */
//http://localhost:8080/mm/store/queryPage?****
@WebServlet("/store/company/*")
public class CompanyServlet extends BaseServlet{

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
            PageInfo<Company> list = companyService.findAllCompany(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true,MessageConstant.QUERY_COMPANY_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_COMPANY_SUCCESS);
        }
    }


    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            companyService.delete(ids);
            return new Result(true,MessageConstant.DELETE_COMPANY_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_COMPANY_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Company company = getData(req,Company.class);
            companyService.update(company);
            return new Result(true,MessageConstant.EDIT_COMPANY_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_COMPANY_FAIL);
        }

    }



    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Company company = companyService.findCompanyById(id);
            return new Result(true,MessageConstant.QUERY_COMPANY_SUCCESS,company);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_COMPANY_SUCCESS);
        }

    }

    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Company company = getData(req,Company.class);
            companyService.save(company);
            return new Result(true,MessageConstant.SAVE_COMPANY_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SAVE_COMPANY_FAIL);
        }
    }

    /**
     * 查询所属企业
     * @param req
     * @param resp
     * @return
     */
    protected Result findAllCompany(HttpServletRequest req, HttpServletResponse resp) {
        try {

            List<Company> list = companyService.findAllCompany();
            return new Result(true,MessageConstant.QUERY_DEPT_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_DEPT_FAIL);
        }
    }
}
