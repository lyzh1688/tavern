/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-29 01:03:58
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
INSERT INTO `HRM_ROLE_INFO` VALUES ('ADMIN', 'ADMIN', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('r_cn', '出纳', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('r_cwzg', '财务主管', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('r_khjl', '客户经理', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('r_sczg', '市场主管', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('r_sq', '售前', null);

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
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('1', 'ADMIN', 'ADMIN', null, '123', '1', '2019-08-08 00:00:00');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('2', 'SQ_1', 'SQ_1', null, '123', '1', '2019-08-28 23:32:27');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('3', 'SQ_2', 'SQ_2', null, '123', '1', '2019-08-28 23:32:45');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('4', 'CWZG', 'CWZG', null, '123', '1', '2019-08-28 23:33:09');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('5', 'KHJL', 'KHJL', null, '123', '1', '2019-08-28 23:33:28');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('6', 'SCZG', 'SCZG', null, '123', '1', '2019-08-28 23:33:51');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('7', 'CN_1', 'CN_1', null, '123', '1', '2019-08-28 23:34:15');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('8', 'CN_2', 'CN_2', null, '123', '1', '2019-08-28 23:34:37');

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
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('1', 'ADMIN', '2019-08-08 23:49:24');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('2', 'r_sq', '2019-08-28 23:39:45');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('3', 'r_sq', '2019-08-28 23:40:18');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('4', 'r_cwzg', '2019-08-08 23:49:24');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('5', 'r_khjl', '2019-08-28 23:39:06');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'r_sczg', '2019-08-28 23:39:27');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('7', 'r_cn', '2019-08-08 23:49:24');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('8', 'r_cn', '2019-08-08 23:49:24');
