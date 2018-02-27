

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` bigint(11) DEFAULT NULL COMMENT '父菜单ID',
  `level` bigint(11) DEFAULT '1' COMMENT '菜单层级(默认为1级菜单)',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_menu`
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu` VALUES ('1', '系统管理', '2018-02-27 15:44:06', null, null, '1', null), ('2', '基础信息', '2018-02-27 15:44:31', null, null, '1', null), ('3', '用户管理', '2018-02-27 15:53:46', null, '2', '2', 'user'), ('4', '角色管理', '2018-02-27 15:54:15', null, '1', '2', 'role'), ('5', '权限管理', '2018-02-27 15:54:49', null, '1', '2', 'perm');
COMMIT;

-- ----------------------------
--  Table structure for `tb_perm`
-- ----------------------------
DROP TABLE IF EXISTS `tb_perm`;
CREATE TABLE `tb_perm` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `url` varchar(50) NOT NULL COMMENT '权限路径',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `menu_id` bigint(50) NOT NULL COMMENT '菜单ID',
  `menu_url` varchar(50) NOT NULL COMMENT '菜单路径',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_perm`
-- ----------------------------
BEGIN;
INSERT INTO `tb_perm` VALUES ('1', 'create', '添加', '4', 'role', '2018-02-27 16:03:13', null), ('2', 'update', '修改', '4', 'role', '2018-02-27 16:03:34', null), ('3', 'delete', '删除', '4', 'role', '2018-02-27 16:03:54', null), ('4', 'list', '分页查询', '4', 'role', '2018-02-27 16:05:07', null), ('5', 'find', '查询', '4', 'role', '2018-02-27 16:05:38', null), ('6', 'create', '添加', '3', 'user', '2018-02-27 16:10:14', null), ('7', 'update', '修改', '3', 'user', '2018-02-27 16:10:37', null), ('8', 'delete', '删除', '3', 'user', '2018-02-27 16:10:54', null), ('9', 'list', '分页查询', '3', 'user', '2018-02-27 16:11:12', null), ('10', 'find', '查询', '3', 'user', '2018-02-27 16:11:32', null);
COMMIT;

-- ----------------------------
--  Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_role`
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` VALUES ('1', '系统管理员', '2018-02-27 15:59:07', null);
COMMIT;

-- ----------------------------
--  Table structure for `tb_role_perm`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_perm`;
CREATE TABLE `tb_role_perm` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(11) NOT NULL COMMENT '角色ID',
  `perm_id` bigint(11) NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(11) NOT NULL COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '昵称',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `status` int(2) DEFAULT NULL COMMENT '账号状态(1:启用,0:禁用)',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `creater` bigint(11) DEFAULT NULL,
  `updater` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_user`
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('1', '系统管理员', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '1', '2018-02-27 15:58:09', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `role_id` bigint(11) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
