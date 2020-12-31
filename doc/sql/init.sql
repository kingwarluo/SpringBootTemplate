-- 用户表
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '加密密码的盐值',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `t_user` (`id`, `name`, `password`, `salt`, `sex`, `mobile`, `birthday`) VALUES ('1', 'admin', 'f2d6b5190799468030431a192cf53f30', 'e399abe54c2946a4af8f21d862baa1d2', '1', '15659455896', '2020-12-17 15:31:59');

-- 菜单表

-- 权限表