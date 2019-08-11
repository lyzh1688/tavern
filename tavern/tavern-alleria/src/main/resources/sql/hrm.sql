/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-12 00:40:52
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
  `ROLE_ID` varchar(45) CHARACTER SET utf8 NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `PARENT_ROLE` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '上级角色ID',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='角色信息表';

-- ----------------------------
-- Records of HRM_ROLE_INFO
-- ----------------------------
INSERT INTO `HRM_ROLE_INFO` VALUES ('12', '研发经理', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('13', '研发副总', null);

-- ----------------------------
-- Table structure for HRM_USER_BASIC_INFO
-- ----------------------------
DROP TABLE IF EXISTS `HRM_USER_BASIC_INFO`;
CREATE TABLE `HRM_USER_BASIC_INFO` (
  `USER_ID` varchar(45) CHARACTER SET utf8 NOT NULL COMMENT '用户ID内码',
  `USER_NAME` varchar(45) CHARACTER SET utf8 NOT NULL COMMENT '用户姓名',
  `USER_ACCNT` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户登录账号',
  `SALT` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '盐',
  `PASSWORD` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户登录密码',
  `IS_VALID` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of HRM_USER_BASIC_INFO
-- ----------------------------
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('1', 'HE', 'H', null, '123', '1', '2019-08-08 00:00:00');

-- ----------------------------
-- Table structure for HRM_USER_ROLE_REL
-- ----------------------------
DROP TABLE IF EXISTS `HRM_USER_ROLE_REL`;
CREATE TABLE `HRM_USER_ROLE_REL` (
  `USER_ID` varchar(45) CHARACTER SET utf8 NOT NULL,
  `ROLE_ID` varchar(45) CHARACTER SET utf8 NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of HRM_USER_ROLE_REL
-- ----------------------------
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('1', '12', '2019-08-08 23:49:24');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('1', '13', '2019-08-08 23:49:24');
