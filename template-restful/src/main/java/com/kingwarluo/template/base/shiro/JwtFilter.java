package com.kingwarluo.template.base.shiro;

import com.alibaba.fastjson.JSON;
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

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public JwtFilter(ShiroProperties shiroProperties) {
        this.shiroProperties = shiroProperties;
    }

    /**
     * 执行登录认证(判断请求头是否带上token)
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue) && hasPermission(request);
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
            String uri = request.getRequestURI();
            Subject subject = Objects.requireNonNull(SecurityUtils.getSubject());
            if(subject.isAuthenticated()) {
                return true;
            } else {
                String[] noLoginUrls = shiroProperties.getNoLoginUrls().split(",");
                for (String noLoginUrl : noLoginUrls) {
                    if(antPathMatcher.match(noLoginUrl, uri)){
                        log.info("JwtFilter，[{}]请求无需登录", uri);
                        return true;
                    }
                }
                onAccessDenied(response);
            }
        } catch (Exception e) {
            log.error("SYSTEM ERROR", e);
        }
        return false;
    }

    private void onAccessDenied(HttpServletResponse response) throws Exception {
        setRes(response, HttpServletResponse.SC_UNAUTHORIZED,
                401, false, 401);
    }

    private void setRes(HttpServletResponse response, int status, Object data, Boolean success, int code) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("success", success);
        result.put("description", success ? "请求成功" : "请求失败");
        result.put("data", data);
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(status);
        response.getWriter().write(JSON.toJSONString(result));
    }
}