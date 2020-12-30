package com.kingwarluo.template.base.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
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

    // 这是做啥用的
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    // 权限管理
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();//拦截器, 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/user/login","anon");    //所有匿名用户均可访问到Controller层的该方法下
        filterChainDefinitionMap.put("/userLogin","anon");
        filterChainDefinitionMap.put("/image/**","anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/**", "authc");    //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        //filterChainDefinitionMap.put("/**", "user");   //user表示配置记住我或认证通过可以访问的地址

        // 添加自己的过滤器并且取名为jwt
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", jwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // 过滤链定义，从上向下顺序执行，一般将放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

}
