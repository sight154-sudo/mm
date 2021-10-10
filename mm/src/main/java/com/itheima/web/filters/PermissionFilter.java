package com.itheima.web.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.constant.MessageConstant;
import com.itheima.domain.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author: tang
 * @date: Create in 20:29 2021/7/2
 * @description:用户权限过滤器
 */
@WebFilter(urlPatterns = {"/system/*","/store/*"})
public class PermissionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        //获取请求servlet的路径
        String servletPath = req.getServletPath();
        System.out.println(servletPath);///system/module
        //获取请求路径上的最后路径
        String pathInfo = req.getPathInfo();
        System.out.println(pathInfo); ////findMenu
        filterChain.doFilter(req,resp);

        if(pathInfo.substring(1).startsWith("find") ||
                pathInfo.substring(1).startsWith("query")){
            filterChain.doFilter(req,resp);
            return;
        }
        HttpSession session = req.getSession();
//        uri  /mm/pages/company.html
//        curl  system/dept   system   system/module   system/user  /system/dept/queryPage
        List<String> curl = (List<String>) session.getAttribute("curl");
        String url = servletPath.substring(1)+pathInfo;
        if(curl.contains(url)){
            filterChain.doFilter(req,resp);
            return;
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getOutputStream(),new Result(false, MessageConstant.PERMISSION_FORBIDDEN));
        }
    }

    @Override
    public void destroy() {

    }
}
