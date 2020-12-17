package com.kingwarluo.template.modules.user.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author jianhua.luo
 * @date 2020/12/17
 */
@Data
public class User {

    private Long id;

    private String name;

    private String password;

    private String mobile;

    private Date birthday;

    private Integer sex;

}
