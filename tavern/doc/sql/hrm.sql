/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-09-19 02:01:06
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
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_CN', '出纳', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_CWZG', '财务主管', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_KHJL', '客户经理', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_SCZG', '市场主管', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_SQ', '售前', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_TEST', '测试角色1', null);

-- ----------------------------
-- Table structure for HRM_USER_BASIC_INFO
-- ----------------------------
DROP TABLE IF EXISTS `HRM_USER_BASIC_INFO`;
CREATE TABLE `HRM_USER_BASIC_INFO` (
  `USER_ID` varchar(45) NOT NULL COMMENT '用户ID内码',
  `USER_NAME` varchar(45) CHARACTER SET utf8 NOT NULL COMMENT '用户姓名',
  `USER_ACCNT` varchar(45) CHARACTER SET utf8 NOT NULL COMMENT '用户登录账号',
  `SALT` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '盐',
  `PASSWORD` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户登录密码',
  `IS_VALID` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`USER_ACCNT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of HRM_USER_BASIC_INFO
-- ----------------------------
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('1', 'ADMIN', 'ADMIN', null, '123', '1', '2019-08-08 00:00:00');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('2', '我是售前', 'SQ_1', null, '123', '1', '2019-08-28 16:50:24');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('3', '我是出纳', 'CN_1', null, '123', '1', '2019-08-28 16:51:07');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('4', '我是财务主管1', 'CWZG1', null, '123', '1', '2019-08-28 16:51:32');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('5', '我是客户经理', 'KHJL_1', null, '123', '1', '2019-08-28 16:51:52');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'he', 'he', '44a73845973f404e8202', '709651d1966bdf39bfa5c91b7c7d2b08', '1', '2019-09-19 01:00:22');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('6', '我是市场主管1', 'SCZG1', null, '123', '1', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('7', '我是市场主管2', 'SCZG2', '', '123', '1', '2019-08-28 16:52:09');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('8', '我是财务主管2', 'CWZG2', '', '123', '1', '2019-08-28 16:51:32');

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
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('2', 'R_SQ', '2019-08-08 23:49:24');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('3', 'R_CN', '2019-08-28 16:53:49');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('4', 'R_CWZG', '2019-08-28 16:54:05');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('5', 'R_KHJL', '2019-08-28 16:54:17');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'R_TEST', '2019-09-19 01:00:22');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_CN', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_CWZG', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_KHJL', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_SCZG', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('7', 'R_SCZG', '2019-08-30 11:15:46');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('8', 'R_CWZG', '2019-08-30 11:16:01');

-- ----------------------------
-- Procedure structure for UTF8
-- ----------------------------
DROP PROCEDURE IF EXISTS `UTF8`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `UTF8`()
BEGIN
 
    declare stop int default 0;
 
    declare tabCount int default 0;
 
    declare strSql varchar(1000);
 
    declare name varchar(100);
 
    declare cur CURSOR FOR select table_name from information_schema.tables where table_schema='hrm';
 
    declare CONTINUE HANDLER FOR SQLSTATE '02000' SET stop = null;
 
    OPEN cur;
 
    FETCH cur INTO name;
 
    WHILE ( stop is not null) DO
 
        set tabCount=tabCount+1;
 
        set strSql = concat('alter table `',name,'` convert to character set utf8');
 
        set @sql1 = strSql;
 
        prepare stmt_p from @sql1;
 
        execute stmt_p;
 
        FETCH cur INTO name;
 
    END WHILE;
 
    CLOSE cur;
 
    SELECT concat('table: ', tabCount);
 
END
;;
DELIMITER ;
