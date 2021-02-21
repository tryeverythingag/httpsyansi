package com.java.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ron
 * @data 2020/1/15- 15:00
 */
@WebFilter(urlPatterns = {"/*"})
public class KuayuFilterKuayuFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest res, ServletResponse req, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) res;
        HttpServletResponse response=(HttpServletResponse)req;
        //允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*"); // 设置允许所有跨域访问
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //放行
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
