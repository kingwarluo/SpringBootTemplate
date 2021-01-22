package com.kingwarluo.template.http.user.controller;

import com.kingwarluo.template.base.shiro.JwtUtil;
import com.kingwarluo.template.base.vo.Result;
import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.modules.user.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
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
        // 生成token
        String token = JwtUtil.sign(account, password);
        return Result.suc(token);
    }

    @RequestMapping("/info")
    public Result getUserByNameAndPassword(String token) {
        String username = JwtUtil.getUserAccount(token);
        return Result.suc(userService.getUserByAccount(username));
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request) {
        return Result.suc(true);
    }

}
