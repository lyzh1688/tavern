/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-09-18 18:48:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hrm_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `hrm_role_auth`;
CREATE TABLE `hrm_role_auth` (
  `ROLE_ID` varchar(45) NOT NULL,
  `AUTH_TYPE` varchar(45) NOT NULL COMMENT 'C,U,R,D',
  `ITEM` varchar(45) NOT NULL,
  `ITEM_TYPE` varchar(45) NOT NULL COMMENT '1:MENU,2:BUTTON',
  PRIMARY KEY (`ROLE_ID`,`ITEM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hrm_role_auth
-- ----------------------------

-- ----------------------------
-- Table structure for hrm_role_info
-- ----------------------------
DROP TABLE IF EXISTS `hrm_role_info`;
CREATE TABLE `hrm_role_info` (
  `ROLE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PARENT_ROLE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上级角色ID',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='角色信息表';

-- ----------------------------
-- Records of hrm_role_info
-- ----------------------------
INSERT INTO `hrm_role_info` VALUES ('ADMIN', 'ADMIN', null);
INSERT INTO `hrm_role_info` VALUES ('R_CN', '出纳', null);
INSERT INTO `hrm_role_info` VALUES ('R_CWZG', '财务主管', null);
INSERT INTO `hrm_role_info` VALUES ('R_KHJL', '客户经理', null);
INSERT INTO `hrm_role_info` VALUES ('R_SCZG', '市场主管', null);
INSERT INTO `hrm_role_info` VALUES ('R_SQ', '售前', null);
INSERT INTO `hrm_role_info` VALUES ('R_TEST', '测试角色1', null);

-- ----------------------------
-- Table structure for hrm_user_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `hrm_user_basic_info`;
CREATE TABLE `hrm_user_basic_info` (
  `USER_ID` varchar(45) NOT NULL COMMENT '用户ID内码',
  `USER_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `USER_ACCNT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录账号',
  `SALT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐',
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户登录密码',
  `IS_VALID` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`USER_ACCNT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hrm_user_basic_info
-- ----------------------------
INSERT INTO `hrm_user_basic_info` VALUES ('1', 'ADMIN', 'ADMIN', null, '123', '1', '2019-08-08 00:00:00');
INSERT INTO `hrm_user_basic_info` VALUES ('2', '我是售前', 'SQ_1', null, '123', '1', '2019-08-28 16:50:24');
INSERT INTO `hrm_user_basic_info` VALUES ('3', '我是出纳', 'CN_1', null, '123', '1', '2019-08-28 16:51:07');
INSERT INTO `hrm_user_basic_info` VALUES ('4', '我是财务主管1', 'CWZG1', null, '123', '1', '2019-08-28 16:51:32');
INSERT INTO `hrm_user_basic_info` VALUES ('5', '我是客户经理', 'KHJL_1', null, '123', '1', '2019-08-28 16:51:52');
INSERT INTO `hrm_user_basic_info` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'he', 'he', '99a98cff2d5446579d18', '7aba6d235d7225b488838a8c617aca52', '0', '2019-09-18 18:40:15');
INSERT INTO `hrm_user_basic_info` VALUES ('6', '我是市场主管1', 'SCZG1', null, '123', '1', '2019-09-18 18:48:00');
INSERT INTO `hrm_user_basic_info` VALUES ('7', '我是市场主管2', 'SCZG2', '', '123', '1', '2019-08-28 16:52:09');
INSERT INTO `hrm_user_basic_info` VALUES ('8', '我是财务主管2', 'CWZG2', '', '123', '1', '2019-08-28 16:51:32');

-- ----------------------------
-- Table structure for hrm_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `hrm_user_role_rel`;
CREATE TABLE `hrm_user_role_rel` (
  `USER_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ROLE_ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of hrm_user_role_rel
-- ----------------------------
INSERT INTO `hrm_user_role_rel` VALUES ('1', 'ADMIN', '2019-08-08 23:49:24');
INSERT INTO `hrm_user_role_rel` VALUES ('2', 'R_SQ', '2019-08-08 23:49:24');
INSERT INTO `hrm_user_role_rel` VALUES ('3', 'R_CN', '2019-08-28 16:53:49');
INSERT INTO `hrm_user_role_rel` VALUES ('4', 'R_CWZG', '2019-08-28 16:54:05');
INSERT INTO `hrm_user_role_rel` VALUES ('5', 'R_KHJL', '2019-08-28 16:54:17');
INSERT INTO `hrm_user_role_rel` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'R_CN', '2019-09-18 18:30:41');
INSERT INTO `hrm_user_role_rel` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'R_CWZG', '2019-09-18 18:30:41');
INSERT INTO `hrm_user_role_rel` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'R_KHJL', '2019-09-18 18:30:41');
INSERT INTO `hrm_user_role_rel` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'R_SCZG', '2019-09-18 18:30:41');
INSERT INTO `hrm_user_role_rel` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'R_SQ', '2019-09-18 18:30:41');
INSERT INTO `hrm_user_role_rel` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'R_TEST', '2019-09-18 18:30:41');
INSERT INTO `hrm_user_role_rel` VALUES ('6', 'R_CN', '2019-09-18 18:48:00');
INSERT INTO `hrm_user_role_rel` VALUES ('6', 'R_CWZG', '2019-09-18 18:48:00');
INSERT INTO `hrm_user_role_rel` VALUES ('6', 'R_KHJL', '2019-09-18 18:48:00');
INSERT INTO `hrm_user_role_rel` VALUES ('6', 'R_SCZG', '2019-09-18 18:48:00');
INSERT INTO `hrm_user_role_rel` VALUES ('7', 'R_SCZG', '2019-08-30 11:15:46');
INSERT INTO `hrm_user_role_rel` VALUES ('8', 'R_CWZG', '2019-08-30 11:16:01');
