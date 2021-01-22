/*
* Copyright (C), 2021-2025, KingWarLuo
*/
package com.kingwarluo.template.modules.user.dao;

import com.kingwarluo.template.modules.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import com.kingwarluo.template.base.mybatis.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

/**
 *  Dao 接口
 *
 * @author jianhua.luo
 * @since 2021-01-22
 */
@Mapper
public interface UserDao extends IBaseDao<User>{

    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    User getUserByAccount(@Param("account") String account);
}
