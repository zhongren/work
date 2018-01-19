/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.18-log : Database - db_admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_admin`;

/*Table structure for table `tb_perm` */

DROP TABLE IF EXISTS `tb_perm`;

CREATE TABLE `tb_perm` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `url` varchar(50) NOT NULL COMMENT '权限基础路径',
  `operation` varchar(50) DEFAULT NULL COMMENT '权限具体操作',
  `desc` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_role_perm` */

DROP TABLE IF EXISTS `tb_role_perm`;

CREATE TABLE `tb_role_perm` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) NOT NULL COMMENT '角色ID',
  `perm_id` bigint(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '用户状态(0:有效,-1:禁用)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creater` bigint(11) DEFAULT NULL,
  `updater` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_user_role` */

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `role_id` bigint(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
