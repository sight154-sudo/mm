package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.QuestionItem;
import com.itheima.domain.Result;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 20:23 2021/7/1
 * @description:
 */
@WebServlet("/store/questionItem/*")
public class QuestionItemServlet extends BaseServlet {
    protected Result queryPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String currentPageStr = req.getParameter("currentPage");
            String pageSizeStr = req.getParameter("pageSize");
            Integer currentPage, pageSize;
            currentPage = Integer.valueOf(currentPageStr);
            pageSize = Integer.valueOf(pageSizeStr);
            if (StringUtils.isBlank(currentPageStr)) {
                currentPage = 1;
            }
            if (StringUtils.isBlank(pageSizeStr)) {
                pageSize = 5;
            }
            PageInfo<QuestionItem> list = questionItemService.findAllQuestionItem(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true, MessageConstant.QUERY_QUESTIONITEM_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_QUESTIONITEM_SUCCESS);
        }
    }


    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            questionItemService.delete(id);
            return new Result(true, MessageConstant.DELETE_QUESTIONITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_QUESTIONITEM_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            QuestionItem questionItem = getData(req, QuestionItem.class);
            questionItemService.update(questionItem);
            return new Result(true, MessageConstant.EDIT_QUESTIONITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_QUESTIONITEM_FAIL);
        }

    }


    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            QuestionItem questionItem = questionItemService.findQuestionItemById(id);
            return new Result(true, MessageConstant.QUERY_QUESTIONITEM_SUCCESS, questionItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_QUESTIONITEM_SUCCESS);
        }

    }
    protected Result findAll(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("questionId");
            List<QuestionItem> list = questionItemService.findAllQuestionItemByQid(id);
            return new Result(true, MessageConstant.QUERY_QUESTIONITEM_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_QUESTIONITEM_SUCCESS);
        }

    }
    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            QuestionItem questionItem = getData(req, QuestionItem.class);
            String id = null;
            if(StringUtils.isNotBlank(questionItem.getId())){
                questionItemService.update(questionItem);
            }else{
                id = questionItemService.save(questionItem);
            }
            return new Result(true, MessageConstant.SAVE_QUESTIONITEM_SUCCESS,id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SAVE_QUESTIONITEM_FAIL);
        }
    }

    /**
     * 文件上传方法
     *
     * @param req
     * @param resp
     * @return
     */
    protected Result upload(HttpServletRequest req, HttpServletResponse resp) {
        try {

            //获取磁盘工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //创建文件上传对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            List<FileItem> fileItems = fileUpload.parseRequest(req);
            String filename = null;
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    //是文件上传
                    String name = fileItem.getName();
                    //获取文件后缀名
                    String suffix = name.substring(name.lastIndexOf("."));
//                    生成随机文件名
                    String str = UUID.randomUUID().toString().replace("-", "");
                    filename = str + suffix;
                    //上传文件
                    String upload = this.getServletContext().getRealPath("/upload");
                    fileItem.write(new File(upload, filename));
                }
            }
            return new Result(true, MessageConstant.UPLOAD_QUESTIONIMG_SUCCESS, filename);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true, MessageConstant.UPLOAD_QUESTIONIMG_FAIL);
    }
}
