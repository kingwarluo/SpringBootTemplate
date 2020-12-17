package com.kingwarluo.template.http.user.controller;

import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/getByNameAndPassword")
    public User getUserByNameAndPassword(String name, String password) {
        return userService.getUserByNameAndPassword(name, password);
    }

}
