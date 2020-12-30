package com.kingwarluo.template.modules.user.service.impl;

import com.kingwarluo.template.modules.user.dao.UserDao;
import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author jianhua.luo
 * @date 2020/12/17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名密码获取用户
     *
     * @author jianhua.luo
     * @date 2020/12/17
     */
    @Override
    public User getUserByNameAndPassword(String name, String password) {
        return userDao.getUserByNameAndPassword(name, password);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
