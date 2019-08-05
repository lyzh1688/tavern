/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-05 22:44:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for HRM_ROLE_AUTH
-- ----------------------------
DROP TABLE IF EXISTS `HRM_ROLE_AUTH`;
CREATE TABLE `HRM_ROLE_AUTH` (
  `ROLE_ID` varchar(45) NOT NULL,
  `AUTH_TYPE` varchar(45) NOT NULL COMMENT 'C,U,R,D',
  `ITEM` varchar(45) NOT NULL,
  `ITEM_TYPE` varchar(45) NOT NULL COMMENT '1:MENU,2:BUTTON',
  PRIMARY KEY (`ROLE_ID`,`ITEM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of HRM_ROLE_AUTH
-- ----------------------------

-- ----------------------------
-- Table structure for HRM_ROLE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `HRM_ROLE_INFO`;
CREATE TABLE `HRM_ROLE_INFO` (
  `ROLE_ID` varchar(45) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(45) DEFAULT NULL,
  `PARENT_ROLE` varchar(45) DEFAULT NULL COMMENT '上级角色ID',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='角色信息表';

-- ----------------------------
-- Records of HRM_ROLE_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for HRM_USER_BASIC_INFO
-- ----------------------------
DROP TABLE IF EXISTS `HRM_USER_BASIC_INFO`;
CREATE TABLE `HRM_USER_BASIC_INFO` (
  `USER_ID` varchar(45) NOT NULL COMMENT '用户ID内码',
  `USER_NAME` varchar(45) DEFAULT NULL COMMENT '用户姓名',
  `USER_ACCNT` varchar(45) DEFAULT NULL COMMENT '用户登录账号',
  `PASSWORD` varchar(45) DEFAULT NULL COMMENT '用户登录密码',
  `IS_VALID` varchar(1) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of HRM_USER_BASIC_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for HRM_USER_ROLE_REL
-- ----------------------------
DROP TABLE IF EXISTS `HRM_USER_ROLE_REL`;
CREATE TABLE `HRM_USER_ROLE_REL` (
  `USER_ID` varchar(45) NOT NULL,
  `ROLE_ID` varchar(45) NOT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of HRM_USER_ROLE_REL
-- ----------------------------
