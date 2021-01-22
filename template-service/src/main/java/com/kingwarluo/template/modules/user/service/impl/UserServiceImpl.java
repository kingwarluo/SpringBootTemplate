/*
* Copyright (C), 2021-2025, KingWarLuo
*/
package com.kingwarluo.template.modules.user.service.impl;

import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.modules.user.dao.UserDao;
import com.kingwarluo.template.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author jianhua.luo
 * @since 2021-01-22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /***
     * 插入记录
     * @param user  数据实体类
     * @return 返回插入成功后的主键id
     */
    @Override
    public Object insert(User user) {
        return userDao.insert(user);
    }
    /***
     * 通过id获取
     * @param id 主键id
     * @return 返回实体类
     */
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    /***
     * 通过id更新
     * @param user 实体类中必须有id
     * @return 更新成功的条数
     */
    @Override
    public int updateById(User user) {
        return userDao.updateById(user);
    }

    /**
     *
     * @param account
     * @return
     */
    @Override
    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }
}

