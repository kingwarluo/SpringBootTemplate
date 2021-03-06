package com.kingwarluo.template.http.user.controller;

import com.kingwarluo.template.base.shiro.JwtToken;
import com.kingwarluo.template.base.shiro.JwtUtil;
import com.kingwarluo.template.base.vo.Result;
import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.modules.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户controller
 *
 * @author jianhua.luo
 * @date 2020/12/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Result<String> login(User user) {
        User exist = userService.getUserByAccount(user.getAccount());
        if(exist == null) {
            return Result.failBiz("用户不存在");
        }
        String account = user.getAccount();
        String password = user.getPassword();

        //我的密码是使用uuid作为盐值加密的，所以这里登陆时候还需要做一次对比
        SimpleHash simpleHash = new SimpleHash("MD5", password,  exist.getSalt(), 1024);
        if(!simpleHash.toHex().equals(exist.getPassword())){
            return Result.failBiz("密码不正确");
        }
        // 生成token，这里仅用于将用户名密码加密，提供给realm校验使用
        // 1、如果是ldap，则token用于给子系统登录使用。ldap目录在跳转到子系统时，会给子系统发放一个token
        //    子系统在realm的auth认证时，会带上该token去请求登录信息，实现自动登录
        String token = JwtUtil.sign(account, password);
        JwtToken jwtToken = new JwtToken(token);
        SecurityUtils.getSubject().login(jwtToken);
        return Result.suc(token);
    }

    @RequestMapping("/info")
    public Result getUserByNameAndPassword() {
        Subject subject = SecurityUtils.getSubject();
        return Result.suc(subject.getPrincipal());
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return Result.suc(true);
    }

}
