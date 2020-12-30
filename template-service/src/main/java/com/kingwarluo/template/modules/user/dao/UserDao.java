package com.kingwarluo.template.modules.user.dao;

import com.kingwarluo.template.modules.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户dao
 *
 * @author jianhua.luo
 * @date 2020/12/17
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名密码获取用户
     *
     * @author jianhua.luo
     * @date 2020/12/17
     */
    User getUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     * 根据用户名获取用户
     *
     * @author jianhua.luo
     * @date 2020/12/17
     */
    User getUserByName(@Param("name") String name);

}
