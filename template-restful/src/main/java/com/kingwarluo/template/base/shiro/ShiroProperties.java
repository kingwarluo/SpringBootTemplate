package com.kingwarluo.template.base.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    /**
     * 不需要登录的请求地址，用，隔开
     */
    private String noLoginUrls;

    /**
     * 启用缓存
     */
    private boolean cachingEnabled;

    /**
     * 开启认证缓存
     */
    private boolean authenticationCachingEnabled;

    /**
     * 指定认证缓存的名字
     */
    private String authenticationCacheName;
}
