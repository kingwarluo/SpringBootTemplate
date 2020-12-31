package com.kingwarluo.template.http.user.controller;

import com.kingwarluo.template.base.shiro.JwtUtil;
import com.kingwarluo.template.base.vo.Result;
import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.modules.user.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Result<String> login(@RequestBody User user) {
        User exist = userService.getUserByName(user.getName());
        if(exist == null) {
            return Result.failBiz("用户不存在");
        }
        String username = user.getName();
        String password = user.getPassword();

        //我的密码是使用uuid作为盐值加密的，所以这里登陆时候还需要做一次对比
        SimpleHash simpleHash = new SimpleHash("MD5", password,  exist.getSalt(), 1024);
        if(!simpleHash.toHex().equals(exist.getPassword())){
            return Result.failBiz("密码不正确");
        }
        // 生成token TODO 放到redis
        String token = JwtUtil.sign(username, password);
        return Result.suc(token);
    }

    @RequestMapping("/info")
    public Result getUserByNameAndPassword(HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
        String username = JwtUtil.getUsername(token);
        return Result.suc(userService.getUserByName(username));
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request) {
        return Result.suc(true);
    }

}
