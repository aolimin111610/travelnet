package com.test.travelnet.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/4 17:54
 * @Version V1.0
 */
@WebFilter("/*")
public class CharchaterFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String method = request.getMethod();
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }

        response.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
