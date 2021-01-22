-- 用户表
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '加密密码的盐值',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `head_image` varchar(255) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- admin 111111
INSERT INTO `t_user` (`id`, `account`, `name`, `password`, `salt`, `sex`, `mobile`, `birthday`, `head_image`) VALUES ('1', 'admin', 'admin', 'f2d6b5190799468030431a192cf53f30', 'e399abe54c2946a4af8f21d862baa1d2', '1', '15659455896', '2020-12-17 15:31:59', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif');

-- 菜单表
CREATE TABLE `t_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级菜单id',
  `level` tinyint(4) DEFAULT NULL COMMENT '菜单等级',
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(20) DEFAULT NULL COMMENT '菜单编码',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `permission_type` tinyint(4) DEFAULT NULL COMMENT '按钮权限 1:菜单 2:按钮',
  `visible` tinyint(4) DEFAULT NULL COMMENT '是否可见 0:否 1:是',
  `expand` tinyint(4) DEFAULT NULL COMMENT '是否可见 0:否 1:是',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 权限表
CREATE TABLE `t_user_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;