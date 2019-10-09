/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-10-10 02:06:10
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
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_SH', '售后', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_SQ', '售前', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_WQ', '外勤', null);
INSERT INTO `HRM_ROLE_INFO` VALUES ('R_WQZG', '外勤主管', null);

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
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('2', '我是售前1', 'SQ_1', null, '123', '1', '2019-09-21 21:56:53');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('3', '我是出纳1', 'CN_1', null, '123', '1', '2019-09-21 21:57:32');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('4', '我是财务主管1', 'CWZG1', null, '123', '1', '2019-08-28 16:51:32');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('42ed651d283b4019aaab51117063b5f1', '我是外勤2', 'WQ_2', '35b9800f20da43bb8a40', '45a920c9e77a6957c777c5f867e20d85', '1', '2019-09-21 21:58:14');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('5', '我是客户经理', 'KHJL_1', null, '123', '1', '2019-08-28 16:51:52');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'he', 'he', '44a73845973f404e8202', '709651d1966bdf39bfa5c91b7c7d2b08', '1', '2019-09-22 00:03:50');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('6', '我是市场主管1', 'SCZG1', null, '123', '1', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('7', '我是市场主管2', 'SCZG2', '', '123', '1', '2019-08-28 16:52:09');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('8', '我是财务主管2', 'CWZG2', '', '123', '1', '2019-08-28 16:51:32');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('894617b1ab9d4289a0192f6556579d03', '我是外勤1', 'WQ_1', 'b5e4875cff70492e8e8b', '5855e5d4f3b654dbfd4b81a50d094fc6', '1', '2019-09-21 23:57:45');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('a286d363052946d5a88b5e824c590540', '我是售后_1', 'SH_1', '330937078182412890f9', '62f3a91d97ede62c7b7a5559b486fc36', '1', '2019-09-25 11:24:17');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('abcd234352974aa084b7bfa4d32ee565', '我是客户经理1', 'KHJL1', '3a8dbc0aeea54e50904d', '4d97168509bc4e98bf6783bf9c3bccca', '1', '2019-09-26 00:52:34');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('c377a64ceb244ce0a3174e75393f1820', '我是售后2', 'SH_2', '5e5e9e2fa8c84bf3a7b2', 'ecd4bc6cf53d661a2ac48894bdc59c30', '1', '2019-09-25 11:24:45');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('cdacf700cf484ee68163c5fcdf21906d', '我是外勤主管', 'WQZG', '8122d7ff6921448fb6df', 'f9e7b62aa40ff83687cf6695158a4278', '1', '2019-09-21 21:58:07');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('d5f5fcb549a04dd28e4bc81b471dccf7', '我是出纳2', 'CN_2', 'ff324cd13e994798bee3', 'ecf744bb41ae32306a4b01640a999b15', '1', '2019-09-21 21:57:48');
INSERT INTO `HRM_USER_BASIC_INFO` VALUES ('e99bc51c82474ba28a3330be48fb3899', '我是售前2', 'SQ_2', '7dc3fadd2416454dbbdf', '1633ea63b9596956789e10d215c875f9', '1', '2019-09-21 21:57:55');

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
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('2', 'R_SQ', '2019-09-21 21:56:53');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('3', 'R_CN', '2019-09-21 21:57:32');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('4', 'R_CWZG', '2019-08-28 16:54:05');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('42ed651d283b4019aaab51117063b5f1', 'R_WQ', '2019-09-21 21:58:14');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('5', 'R_KHJL', '2019-08-28 16:54:17');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('5c024811df8f4e8fbfbe0629ee061c25', 'ADMIN', '2019-09-22 00:03:50');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_CN', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_CWZG', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_KHJL', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('6', 'R_SCZG', '2019-09-18 18:48:00');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('7', 'R_SCZG', '2019-08-30 11:15:46');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('8', 'R_CWZG', '2019-08-30 11:16:01');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('894617b1ab9d4289a0192f6556579d03', 'R_WQ', '2019-09-21 23:57:46');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('a286d363052946d5a88b5e824c590540', 'R_SH', '2019-09-25 11:24:17');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('abcd234352974aa084b7bfa4d32ee565', 'R_KHJL', '2019-09-26 00:52:34');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('c377a64ceb244ce0a3174e75393f1820', 'R_SH', '2019-09-25 11:24:45');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('cdacf700cf484ee68163c5fcdf21906d', 'R_WQZG', '2019-09-21 21:58:07');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('d5f5fcb549a04dd28e4bc81b471dccf7', 'R_CN', '2019-09-21 21:57:48');
INSERT INTO `HRM_USER_ROLE_REL` VALUES ('e99bc51c82474ba28a3330be48fb3899', 'R_SQ', '2019-09-21 21:57:55');

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
