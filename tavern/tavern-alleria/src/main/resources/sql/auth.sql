/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-08 00:07:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for AUTH_MENU
-- ----------------------------
DROP TABLE IF EXISTS `AUTH_MENU`;
CREATE TABLE `AUTH_MENU` (
  `MENU_ID` varchar(45) CHARACTER SET utf8 NOT NULL,
  `MENU_URL` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `MENU_NAME` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `PARENT_ID` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `PERMS` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `TYPE` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `ICON` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单图标',
  `ORDER_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AUTH_MENU
-- ----------------------------

-- ----------------------------
-- Table structure for AUTH_MENU_ROLE_REL
-- ----------------------------
DROP TABLE IF EXISTS `AUTH_MENU_ROLE_REL`;
CREATE TABLE `AUTH_MENU_ROLE_REL` (
  `MENU_ID` varchar(45) CHARACTER SET utf8 NOT NULL,
  `ROLE_ID` varchar(45) CHARACTER SET utf8 NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`MENU_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AUTH_MENU_ROLE_REL
-- ----------------------------
