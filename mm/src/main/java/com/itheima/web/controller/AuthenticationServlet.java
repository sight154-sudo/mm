package com.itheima.web.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.domain.Result;
import com.itheima.domain.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: tang
 * @date: Create in 14:59 2021/7/2
 * @description:权限校验
 */
@WebServlet("/authentication/*")
public class AuthenticationServlet extends BaseServlet{

    /**
     * 查询登陆用户的信息
     * @param req
     * @param resp
     * @return
     */
    protected Result findLogUser(HttpServletRequest req, HttpServletResponse resp) {
        try {

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            return new Result(true,MessageConstant.QUERY_USER_SUCCESS,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.QUERY_USER_FAIL);
        }
    }

    /**
     * 用户登陆
     * @param req
     * @param resp
     * @return
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User user = userService.login(email,password);
            HttpSession session = req.getSession();
            if(user != null){
                //将用户信息存放在session中
                session.setAttribute("user",user);
                //用户登陆成功后  查询用户可访问的路径
                List<String> curl = moduleService.findPermissionByUserId(user.getId());
                //保存到session域中
                session.setAttribute("curl",curl);
                String sendRedirect = (String) session.getAttribute("sendRedirect");
                if(StringUtils.isNotBlank(sendRedirect)){
                    resp.sendRedirect(sendRedirect);
                }else{
                    resp.sendRedirect(req.getContextPath()+"/pages/main.html");
                }
            }else{
                resp.sendRedirect(req.getContextPath()+"/login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户注销
     * @param req
     * @param resp
     * @return
     */
    protected Result logout(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            resp.sendRedirect(req.getContextPath()+"/login.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
