package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Catalog;
import com.itheima.domain.Company;
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
 * @date: Create in 18:49 2021/7/1
 * @description:
 */
@WebServlet("/store/catalog/*")
public class CatalogServlet extends BaseServlet{
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
            PageInfo<Catalog> list = catalogService.findAllCatalog(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true,MessageConstant.QUERY_CATALOG_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CATALOG_SUCCESS);
        }
    }


    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            catalogService.delete(ids);
            return new Result(true,MessageConstant.DELETE_CATALOG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CATALOG_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Catalog catalog = getData(req,Catalog.class);
            catalogService.update(catalog);
            return new Result(true,MessageConstant.EDIT_CATALOG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CATALOG_FAIL);
        }

    }



    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Catalog catalog = catalogService.findCatalogById(id);
            return new Result(true,MessageConstant.QUERY_CATALOG_SUCCESS,catalog);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CATALOG_SUCCESS);
        }

    }

    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Catalog catalog = getData(req,Catalog.class);
            catalogService.save(catalog);
            return new Result(true, MessageConstant.SAVE_CATALOG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SAVE_CATALOG_FAIL);
        }
    }

    protected Result findAllCatalog(HttpServletRequest req, HttpServletResponse resp) {
        try {

            List<Catalog> list = catalogService.findAllCatalog();
            return new Result(true,MessageConstant.QUERY_DEPT_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_DEPT_FAIL);
        }
    }
}
