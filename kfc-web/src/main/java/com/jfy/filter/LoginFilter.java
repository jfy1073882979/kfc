package com.jfy.filter;

import com.jfy.pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "loginFilter",
        urlPatterns =
                {"/toIndex","/cart/addCart/*","/cart/showcartList","/buy","/order/*","/discuss/addDiscuss/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
//获取HttpSession对象
        HttpSession session=request.getSession();
//获取登录的用户对象
        User user=(User) session.getAttribute("user");
        System.out.println(user);
        if(user!=null){
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect("/user/toLogin");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
