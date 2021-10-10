package com.itheima.web.filters;

import com.itheima.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author: tang
 * @date: Create in 19:05 2021/7/2
 * @description:用户登验证过滤器
 */
@WebFilter(urlPatterns={"/pages/*","/system/*","/store/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
       /* //获取请求的路径
        String uri = req.getRequestURI();
//        uri = uri.substring(uri.lastIndexOf("/")+1);

        if(uri.contains("login")
                || uri.endsWith(".woff2")
                || uri.endsWith(".tff")
                || uri.contains("fonts")
                || uri.endsWith(".css")
                || uri.endsWith(".map")
                || uri.endsWith(".js")
                || uri.endsWith(".png")
                || uri.endsWith(".jpg")){
            filterChain.doFilter(req,resp);
            return;
        }*/
        String uri = req.getRequestURI();;
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user == null){
            //将用户访问的路径保存到session
            session.setAttribute("sendRedirect",uri);
            resp.sendRedirect(req.getContextPath()+"/login.html");
            filterChain.doFilter(req,resp);
            return;
        }

        //查询当前所拥有的权限
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
