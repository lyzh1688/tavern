/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-19 01:12:59
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
-- Table structure for CRM_BUSINESS_DICT
-- ----------------------------
DROP TABLE IF EXISTS `CRM_BUSINESS_DICT`;
CREATE TABLE `CRM_BUSINESS_DICT` (
  `BUSINESS_ID` varchar(45) NOT NULL,
  `BUSINESS_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_BUSINESS_DICT
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_COMPANY_BANK_REL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_COMPANY_BANK_REL`;
CREATE TABLE `CRM_COMPANY_BANK_REL` (
  `COMPANY_ID` varchar(45) NOT NULL,
  `BANK_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_COMPANY_BANK_REL
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_COMPANY_BUSINESS
-- ----------------------------
DROP TABLE IF EXISTS `CRM_COMPANY_BUSINESS`;
CREATE TABLE `CRM_COMPANY_BUSINESS` (
  `COMPANY_ID` varchar(45) NOT NULL,
  `BUSINESS_ID` varchar(45) NOT NULL,
  `BEGIN_DATE` date DEFAULT NULL COMMENT '开始时间',
  `END_DATE` date DEFAULT NULL COMMENT '到期时间',
  `UPDATE_DATE` date DEFAULT NULL,
  `IS_VALID` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`,`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_COMPANY_BUSINESS
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_COMPANY_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_COMPANY_INFO`;
CREATE TABLE `CRM_COMPANY_INFO` (
  `COMPANY_ID` varchar(45) NOT NULL,
  `TAX_TYPE` varchar(45) NOT NULL COMMENT '纳税类型',
  `CITY` varchar(45) NOT NULL COMMENT '所在市',
  `DISTRICT` varchar(45) DEFAULT NULL COMMENT '所在区',
  `FINANCE_DISK_TYPE` varchar(45) DEFAULT NULL COMMENT '金税盘种类',
  `TAX_RATE` decimal(10,0) DEFAULT NULL COMMENT '税率',
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_COMPANY_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_BANK_INFO_OLD
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_BANK_INFO_OLD`;
CREATE TABLE `CRM_CUSTOM_BANK_INFO_OLD` (
  `CUSTOM_ID` varchar(45) NOT NULL,
  `BANK_ID` varchar(45) NOT NULL COMMENT '银行代码',
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`,`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户银行信息表';

-- ----------------------------
-- Records of CRM_CUSTOM_BANK_INFO_OLD
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_BASIC_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_BASIC_INFO`;
CREATE TABLE `CRM_CUSTOM_BASIC_INFO` (
  `CUSTOM_ID` varchar(45) NOT NULL,
  `WEIXIN_ACCNT` varchar(45) NOT NULL COMMENT '微信账号',
  `WANGWANG_ACCNT` varchar(45) NOT NULL COMMENT '旺旺账号',
  `CONTACT_PERSON` varchar(45) NOT NULL COMMENT '联系人',
  `CONTACT_NUMBER` varchar(45) NOT NULL COMMENT '联系电话',
  `CORPORATION` varchar(45) DEFAULT NULL COMMENT '法人',
  `CORPORATION_NUMBER` varchar(45) DEFAULT NULL COMMENT '法人电话',
  `CUSTOM_LEVEL` varchar(45) NOT NULL COMMENT '客户等级',
  `CUSTOM_NAME` varchar(45) NOT NULL COMMENT '客户名称',
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_CUSTOM_BASIC_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_BASIC_INFO_OLD
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_BASIC_INFO_OLD`;
CREATE TABLE `CRM_CUSTOM_BASIC_INFO_OLD` (
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
-- Records of CRM_CUSTOM_BASIC_INFO_OLD
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_COMPANY_REL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_COMPANY_REL`;
CREATE TABLE `CRM_CUSTOM_COMPANY_REL` (
  `CUSTOM_ID` varchar(45) NOT NULL,
  `COMPANY_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`CUSTOM_ID`,`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_CUSTOM_COMPANY_REL
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_FINANCE_INFO_OLD
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_FINANCE_INFO_OLD`;
CREATE TABLE `CRM_CUSTOM_FINANCE_INFO_OLD` (
  `CUSTOM_ID` varchar(45) NOT NULL,
  `FINANCE_DISK_TYPE` varchar(45) DEFAULT NULL COMMENT '金税盘类型',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户财务信息表';

-- ----------------------------
-- Records of CRM_CUSTOM_FINANCE_INFO_OLD
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_CUSTOM_ORDER_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_ORDER_INFO`;
CREATE TABLE `CRM_CUSTOM_ORDER_INFO` (
  `CUSTOM_ID` varchar(45) NOT NULL,
  `ORDER_ID` varchar(45) NOT NULL,
  `ORDER_DATE` varchar(45) NOT NULL COMMENT '订单日期',
  `RECEIVABLE_AMT` decimal(10,0) NOT NULL COMMENT '应收金额',
  `PAYABLE_AMT` decimal(10,0) NOT NULL COMMENT '应付金额',
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_CUSTOM_ORDER_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_ORDER_BUSINESS_REL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_ORDER_BUSINESS_REL`;
CREATE TABLE `CRM_ORDER_BUSINESS_REL` (
  `EVENT_ID` varchar(45) NOT NULL COMMENT '事件ID',
  `ORDER_ID` varchar(45) NOT NULL COMMENT '订单ID',
  `BUSINESS_ID` varchar(45) NOT NULL COMMENT '业务类型ID',
  `COMPANY_ID` varchar(45) NOT NULL,
  `OWNER_ID` varchar(45) NOT NULL COMMENT '对接人',
  `NEED_THIRD_PARTY` varchar(1) NOT NULL COMMENT '是否需要合作方',
  `THIRD_PARTY_ID` varchar(45) DEFAULT NULL,
  `THIRD_PARTY_FEE` decimal(10,0) DEFAULT NULL COMMENT '合作方费用',
  `BUSINESS_TAG` varchar(45) NOT NULL,
  `PRE_EVENT_ID` varchar(45) DEFAULT NULL COMMENT '前置任务',
  `REMARK` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_ORDER_BUSINESS_REL
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_ORDER_DLJZ_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_ORDER_DLJZ_DETAIL`;
CREATE TABLE `CRM_ORDER_DLJZ_DETAIL` (
  `EVENT_ID` varchar(45) NOT NULL,
  `ORDER_ID` varchar(45) DEFAULT NULL,
  `BEGIN_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `IS_BEGIN` varchar(1) DEFAULT NULL COMMENT '服务是否开始',
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理记账明细表';

-- ----------------------------
-- Records of CRM_ORDER_DLJZ_DETAIL
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_ORDER_GJJSBDJ_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_ORDER_GJJSBDJ_DETAIL`;
CREATE TABLE `CRM_ORDER_GJJSBDJ_DETAIL` (
  `EVENT_ID` varchar(45) NOT NULL,
  `ORDER_ID` varchar(45) DEFAULT NULL,
  `BUSINESS_ID` varchar(45) DEFAULT NULL,
  `BEGIN_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `EMPLOYEE_NUM` int(11) DEFAULT NULL COMMENT '缴款人数',
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公积金社保代缴明细表';

-- ----------------------------
-- Records of CRM_ORDER_GJJSBDJ_DETAIL
-- ----------------------------

-- ----------------------------
-- Table structure for CRM_ORDER_GSZC_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_ORDER_GSZC_DETAIL`;
CREATE TABLE `CRM_ORDER_GSZC_DETAIL` (
  `EVENT_ID` varchar(45) NOT NULL,
  `ORDER_ID` varchar(45) DEFAULT NULL,
  `ABSENT` varchar(1) DEFAULT NULL COMMENT '是否需要到场，0：到场，1：缺席',
  `REG_LOCATION_TYPE` varchar(45) DEFAULT NULL COMMENT '注册地类型',
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_ORDER_GSZC_DETAIL
-- ----------------------------
