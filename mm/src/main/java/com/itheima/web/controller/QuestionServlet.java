package com.itheima.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Question;
import com.itheima.domain.Result;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 19:06 2021/7/1
 * @description:
 */
@WebServlet("/store/question/*")
public class QuestionServlet extends BaseServlet {


    protected void toExport(HttpServletRequest req, HttpServletResponse resp) {
        //调用service导出题目
        ByteArrayOutputStream os = null;
        try {
            //设置响应的数据格式
            resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            //设置文件名  Content-Disposition","attachment;filename="+filename
            String filename = new String("题目数据.xlsx".getBytes(),"iso8859-1");
            resp.setHeader("Content-Disposition","attachment;filename="+filename);
            os = questionService.toExport();
            ServletOutputStream ops = resp.getOutputStream();
            os.writeTo(ops);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
            PageInfo<Question> list = questionService.findAllQuestion(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            return new Result(true, MessageConstant.QUERY_QUESTION_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_QUESTION_SUCCESS);
        }
    }


    protected Result delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String ids = req.getParameter("ids");
            questionService.delete(ids);
            return new Result(true, MessageConstant.DELETE_QUESTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_QUESTION_FAIL);
        }

    }

    protected Result update(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Question question = getData(req, Question.class);
            questionService.update(question);
            return new Result(true, MessageConstant.EDIT_QUESTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_QUESTION_FAIL);
        }

    }


    protected Result findById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Question question = questionService.findQuestionById(id);
            return new Result(true, MessageConstant.QUERY_QUESTION_SUCCESS, question);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_QUESTION_SUCCESS);
        }

    }

    protected Result save(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Question question = getData(req, Question.class);
            questionService.save(question);
            return new Result(true, MessageConstant.SAVE_QUESTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SAVE_QUESTION_FAIL);
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
