package com.hr.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.util.Constant;

@WebFilter(filterName = "loginFilter", urlPatterns = "/*.html")
public class LoginFilter implements Filter {
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
        System.out.println("----------------------->过滤器被创建");
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI();
        System.out.println("--------------------->过滤器：请求地址"+requestURI);
        if(Constant.loginUrl.equals(requestURI)||requestURI.contains(Constant.AESSET)||Constant.LOGIN_URI.equals(requestURI)) {
         	  filterChain.doFilter(servletRequest, servletResponse);
        }else {
        	  Object obj=request.getSession().getAttribute("user");
              if(obj==null){
              	response.sendRedirect("/login.html");
              	return;
              }else {
              	 filterChain.doFilter(servletRequest, servletResponse);
              }
        }
    }
    @Override
    public void destroy() {
        System.out.println("----------------------->过滤器被销毁");
    }

}