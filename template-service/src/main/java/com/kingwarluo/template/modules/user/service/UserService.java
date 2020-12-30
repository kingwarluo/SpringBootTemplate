package com.kingwarluo.template.modules.user.service;

import com.kingwarluo.template.modules.user.domain.User;

/**
 * 用户服务
 *
 * @author jianhua.luo
 * @date 2020/12/17
 */
public interface UserService {

    /**
     * 根据用户名密码获取用户
     *
     * @author jianhua.luo
     * @date 2020/12/17
     */
    User getUserByNameAndPassword(String name, String password);

    /**
     * 根据用户名获取用户
     *
     * @author jianhua.luo
     * @date 2020/12/17
     */
    User getUserByName(String name);

}
