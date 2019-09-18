/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-09-18 18:48:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE `auth_menu` (
  `MENU_ID` bigint(45) NOT NULL AUTO_INCREMENT,
  `MENU_URL` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MENU_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PARENT_ID` bigint(45) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `PERMS` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `TYPE` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `ORDER_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of auth_menu
-- ----------------------------
INSERT INTO `auth_menu` VALUES ('1', '', '系统管理', '0', '', '0', 'el-icon-setting', '1', '2019-09-17 22:15:54');
INSERT INTO `auth_menu` VALUES ('2', '', '票据管理', '0', '', '0', 'el-icon-message', '2', '2019-09-17 22:37:42');
INSERT INTO `auth_menu` VALUES ('3', '', '银行流水', '2', '', '1', 'el-icon-bank-card', '3', '2019-09-17 22:52:05');
INSERT INTO `auth_menu` VALUES ('4', '/bank/upload', '流水上传', '3', '', '1', 'el-icon-upload', '4', '2019-09-17 22:42:42');
INSERT INTO `auth_menu` VALUES ('5', '/bank/query', '流水查询', '3', '', '1', 'el-icon-search', '5', '2019-09-17 22:43:27');
INSERT INTO `auth_menu` VALUES ('6', '', '发票清单', '2', '', '1', 'el-icon-tickets', '6', '2019-09-17 22:44:39');
INSERT INTO `auth_menu` VALUES ('7', '/invoice/upload', '发票上传', '6', '', '1', 'el-icon-upload', '7', '2019-09-17 22:53:41');
INSERT INTO `auth_menu` VALUES ('8', '/sys/menu', '菜单管理', '1', '', '1', 'el-icon-menu', '8', '2019-09-17 22:56:00');

-- ----------------------------
-- Table structure for auth_menu_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu_role_rel`;
CREATE TABLE `auth_menu_role_rel` (
  `MENU_ID` bigint(45) NOT NULL,
  `ROLE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`MENU_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of auth_menu_role_rel
-- ----------------------------
INSERT INTO `auth_menu_role_rel` VALUES ('2', 'R_SCZG', '2019-09-18 15:18:03');
INSERT INTO `auth_menu_role_rel` VALUES ('3', 'R_SCZG', '2019-09-18 15:18:03');
INSERT INTO `auth_menu_role_rel` VALUES ('4', 'R_SCZG', '2019-09-18 15:18:03');
INSERT INTO `auth_menu_role_rel` VALUES ('5', 'R_SCZG', '2019-09-18 15:18:03');
