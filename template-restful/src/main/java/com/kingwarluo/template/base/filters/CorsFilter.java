package com.kingwarluo.template.base.filters;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域访问配置
 *
 * @author jianhua.luo
 * @date 2021/1/24
 */
@Component
public class CorsFilter implements Filter {

    /***
     * 允许跨域访问的域名
     */
    private static final String ALLOW_ORIGIN = "*";
    private static final String OPTIONS = "OPTIONS";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CorsFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestOrigin = request.getHeader("Origin");
        if(StringUtils.isNotBlank(requestOrigin)){
            // 允许跨域的域名
            response.setHeader("Access-Control-Allow-Origin", requestOrigin);
            // 允许跨域的http 方法
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            // 允许跨域的头部
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            if (OPTIONS.equals(request.getMethod().toUpperCase())) {
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
