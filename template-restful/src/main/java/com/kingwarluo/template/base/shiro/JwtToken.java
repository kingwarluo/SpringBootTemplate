package com.kingwarluo.template.base.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * shiro token use jwt
 *
 * @author jianhua.luo
 * @date 2020/12/30
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
