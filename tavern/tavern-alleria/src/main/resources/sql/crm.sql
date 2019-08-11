/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-12 00:40:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for CRM_BANK_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_BANK_INFO`;
CREATE TABLE `CRM_BANK_INFO` (
  `BANK_ID` varchar(45) NOT NULL,
  `BANK_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行信息表';

-- ----------------------------
-- Records of CRM_BANK_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_BANK_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_BANK_INFO`;
CREATE TABLE `CRM_CUSTOM_BANK_INFO` (
  `CUSTOM_ID` varchar(45) NOT NULL,
  `BANK_ID` varchar(45) NOT NULL COMMENT '银行代码',
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`,`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户银行信息表';

-- ----------------------------
-- Records of CRM_CUSTOM_BANK_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_BASIC_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_BASIC_INFO`;
CREATE TABLE `CRM_CUSTOM_BASIC_INFO` (
  `CUSTOM_ID` varchar(20) NOT NULL COMMENT '客户ID',
  `CUSTOM_ACCNT` varchar(45) DEFAULT NULL COMMENT '''客户账户''',
  `CUSTOM_NAME` varchar(45) DEFAULT NULL COMMENT '''客户名称''',
  `PASSWORD` varchar(45) DEFAULT NULL COMMENT '''客户登陆密码''',
  `CONTACT_NAME` varchar(45) DEFAULT NULL COMMENT '''联系人''',
  `CONTACT_TEL` varchar(45) DEFAULT NULL COMMENT '''联系人电话''',
  `IS_VALID` varchar(1) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户基本信息表';

-- ----------------------------
-- Records of CRM_CUSTOM_BASIC_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_FINANCE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_FINANCE_INFO`;
CREATE TABLE `CRM_CUSTOM_FINANCE_INFO` (
  `CUSTOM_ID` varchar(45) NOT NULL,
  `FINANCE_DISK_TYPE` varchar(45) DEFAULT NULL COMMENT '金税盘类型',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户财务信息表';

-- ----------------------------
-- Records of CRM_CUSTOM_FINANCE_INFO
-- ----------------------------
