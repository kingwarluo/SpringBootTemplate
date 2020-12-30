package com.kingwarluo.template.base.shiro;

import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.modules.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 身份认证
 *
 * @author jianhua.luo
 * @date 2020/12/30
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 访问控制。比如某个用户是否具有某个操作的使用权限（权限交给前端控制，这里返回null）
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 用户身份识别
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tokenStr = (String) token.getPrincipal();
        // jwt 解密
        String username = JwtUtil.getUsername(tokenStr);
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }
        return new SimpleAuthenticationInfo(user, token, ByteSource.Util.bytes(user.getSalt()), getName());
    }

}
