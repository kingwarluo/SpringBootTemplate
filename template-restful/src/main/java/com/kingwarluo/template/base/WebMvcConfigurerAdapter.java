package com.kingwarluo.template.base;

import com.kingwarluo.template.base.interceptor.ErrorPageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置适配器
 *
 * @author jianhua.luo
 * @date 2020/12/17
 */
@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Bean
    public ErrorPageInterceptor errorPageInterceptor() {
        return new ErrorPageInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorPageInterceptor()).addPathPatterns("/**");
    }
}
