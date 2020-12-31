package com.kingwarluo.template.base.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置shiro
 * 参考：https://www.cnblogs.com/red-star/p/12121941.html
 *
 * @author jianhua.luo
 * @date 2020/12/30
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private ShiroProperties shiroProperties;

    // 这是做啥用的
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCachingEnabled(shiroProperties.isCachingEnabled());
        shiroRealm.setAuthenticationCachingEnabled(shiroProperties.isAuthenticationCachingEnabled());
        shiroRealm.setAuthenticationCacheName(shiroProperties.getAuthenticationCacheName());
        return shiroRealm;
    }

    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    // 权限管理
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        // 设置 SecurityUtils 的 securityManager
        SecurityUtils.setSecurityManager(securityManager);

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加自己的过滤器并且取名为jwt
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        JwtFilter jwt = jwtFilter();
        jwt.setSecurityManager(securityManager);
        filterMap.put("jwt", jwt);

        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

}
