/*
* Copyright (C), 2021-2025, KingWarLuo
*/
package com.kingwarluo.template.modules.user.service;

import com.kingwarluo.template.modules.user.domain.User;
import com.kingwarluo.template.base.mybatis.service.BaseService;

/**
 *  服务类
 *
 * @author jianhua.luo
 * @since 2021-01-22
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据账号查找用户
     *
     * @author jianhua.luo
     * @date 2021/1/22
     */
    User getUserByAccount(String account);
}