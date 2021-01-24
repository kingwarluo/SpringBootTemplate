package com.kingwarluo.template.base.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    private ShiroProperties shiroProperties;

    private SecurityManager securityManager;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 本地登出API，用作拦截器判断，无需创建Controller
     */
    private static String logoutPath = "/user/logout";

    /**
     * 本地登入API，用作拦截器判断，无需创建Controller
     */
    private static String loginPath = "/user/login";

    /**
     * 执行登录认证(判断请求头是否带上token)
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return hasPermission(request);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        return handler(httpServletRequest, httpServletResponse);
    }

    /**
     * 判断是否有访问权限（地址或菜单）
     *
     * @author jianhua.luo
     * @date 2021/1/24
     */
    private boolean hasPermission(ServletRequest request) {
        return false;
    }

    private boolean handler(HttpServletRequest request, HttpServletResponse response) {
        try {
            Subject subject = Objects.requireNonNull(SecurityUtils.getSubject());
            if(subject.isAuthenticated()) {
                return true;
            } else {
                String[] noLoginUrls = shiroProperties.getNoLoginUrls().split(",");
                for (String noLoginUrl : noLoginUrls) {
                    if(antPathMatcher.match(noLoginUrl, request.getRequestURI())){
                        log.info("JwtFilter，[{}]请求无需登录", request.getRequestURI());
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            log.error("SYSTEM ERROR", e);
        }
        return false;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

}