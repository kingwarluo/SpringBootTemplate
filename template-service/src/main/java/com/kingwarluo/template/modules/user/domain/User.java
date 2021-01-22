/*
* Copyright (C), 2021-2025, KingWarLuo
*/
package com.kingwarluo.template.modules.user.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author jianhua.luo
 * @since 2021-01-22
*/
@Data
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密密码的盐值
     */
    private String salt;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 头像地址
     */
    private String headImage;


}
