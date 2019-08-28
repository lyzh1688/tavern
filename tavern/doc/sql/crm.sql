/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-29 01:04:33
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
INSERT INTO `CRM_BANK_INFO` VALUES ('33e275bb59fd4377a3b91e7f2a3ad809', '工商银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('4013794eec7d4367a7846b54078d3211', '中信银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('554bd11cea5e47db9580e0f07beffe72', '中国银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('61d10b75defd40c4b44e312017a37285', '招商银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('74225b1951d54b3e858b5a70de11c4f6', '建设银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('9abbbc9f4e434f27ba3dc240cc786aae', '交通银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('abdbe742c7d14c378a8794141faf5775', '光大银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('dd09938b16554b53925ef5ce0fb760d7', '农业银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('e37910cb3cac4e0dbca70184817def54', '民生银行');

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
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('biz_dljz', '代理记账');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('biz_gsbg', '工商变更');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('biz_gszc', '公司注册');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('biz_gszx', '公司注销');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('biz_sbdk', '社保代开');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('biz_sbzc', '商标注册');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('biz_xkzbl', '许可证办理');

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
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9e93aa78df7242b18c980650763973c0', '33e275bb59fd4377a3b91e7f2a3ad809');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9e93aa78df7242b18c980650763973c0', '4013794eec7d4367a7846b54078d3211');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9e93aa78df7242b18c980650763973c0', '554bd11cea5e47db9580e0f07beffe72');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9e93aa78df7242b18c980650763973c0', 'abdbe742c7d14c378a8794141faf5775');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', '33e275bb59fd4377a3b91e7f2a3ad809');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', '4013794eec7d4367a7846b54078d3211');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', '554bd11cea5e47db9580e0f07beffe72');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', '61d10b75defd40c4b44e312017a37285');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', '74225b1951d54b3e858b5a70de11c4f6');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', '9abbbc9f4e434f27ba3dc240cc786aae');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', 'abdbe742c7d14c378a8794141faf5775');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', 'dd09938b16554b53925ef5ce0fb760d7');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', 'e37910cb3cac4e0dbca70184817def54');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('edcb87477e24482da98d0acce3bdb341', '4013794eec7d4367a7846b54078d3211');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('edcb87477e24482da98d0acce3bdb341', '61d10b75defd40c4b44e312017a37285');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('edcb87477e24482da98d0acce3bdb341', '74225b1951d54b3e858b5a70de11c4f6');

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
  `COMPANY_NAME` varchar(255) NOT NULL,
  `TAX_TYPE` varchar(45) NOT NULL COMMENT '纳税类型',
  `PROVINCE` varchar(45) NOT NULL,
  `CITY` varchar(45) NOT NULL COMMENT '所在市',
  `DISTRICT` varchar(45) DEFAULT NULL COMMENT '所在区',
  `FINANCE_DISK_TYPE` varchar(45) DEFAULT NULL COMMENT '金税盘种类',
  `TAX_RATE` decimal(20,5) DEFAULT NULL COMMENT '税率',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_COMPANY_INFO
-- ----------------------------
INSERT INTO `CRM_COMPANY_INFO` VALUES ('9e93aa78df7242b18c980650763973c0', '盒马生鲜', '1', '上海市', '上海市', '嘉定区', '1', '12.90000', '2019-08-25 22:03:36');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('a5b24848e32d47fd9472de4323d61da2', '阿里妈妈', '0', '海南省', '省直辖县级行政单位', '儋州市', '0', '1111.33400', '2019-08-26 00:13:48');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('edcb87477e24482da98d0acce3bdb341', '天猫商城', '0', '浙江省', '杭州市', '拱墅区', '1', '2.00000', '2019-08-26 21:41:54');

-- ----------------------------
-- Table structure for CRM_COMPANY_STAFF_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_COMPANY_STAFF_INFO`;
CREATE TABLE `CRM_COMPANY_STAFF_INFO` (
  `STAFF_ID` varchar(45) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `ID_CARD` varchar(100) DEFAULT NULL,
  `CONTACT_NUMBER` varchar(45) DEFAULT NULL,
  `NEED_SOCIAL_INS` varchar(10) DEFAULT '0',
  `NEED_HABITATION_INS` varchar(10) DEFAULT '0',
  `NEED_SOCIAL_SERVER` varchar(10) DEFAULT '0',
  `NEED_HABITATION_SERVER` varchar(10) DEFAULT '0',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_COMPANY_STAFF_INFO
-- ----------------------------
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('8020dd4460064084a2c6d661a38ed4a9', '蔡崇信', '343', '14124', '1', '1', '0', '1', '2019-08-26 23:46:22');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('b7c00c107daa411bbb035c50fe699ce5', '张勇', '1234', '414', '1', '1', '0', '2', '2019-08-26 23:48:38');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('c0c6e3ad58304087a17801e74fe0f770', '马云', '324', '32332', '1', '1', '1', '1', '2019-08-26 23:44:00');

-- ----------------------------
-- Table structure for CRM_COMPANY_STAFF_REL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_COMPANY_STAFF_REL`;
CREATE TABLE `CRM_COMPANY_STAFF_REL` (
  `COMPANY_ID` varchar(45) NOT NULL,
  `STAFF_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`,`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_COMPANY_STAFF_REL
-- ----------------------------
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', '8020dd4460064084a2c6d661a38ed4a9');
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('a5b24848e32d47fd9472de4323d61da2', 'c0c6e3ad58304087a17801e74fe0f770');

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
  `WEIXIN_NAME` varchar(45) DEFAULT NULL,
  `WANGWANG_ACCNT` varchar(45) NOT NULL COMMENT '旺旺账号',
  `CONTACT_PERSON` varchar(45) NOT NULL COMMENT '联系人',
  `CONTACT_NUMBER` varchar(45) NOT NULL COMMENT '联系电话',
  `CORPORATION` varchar(45) DEFAULT NULL COMMENT '法人',
  `CORPORATION_NUMBER` varchar(45) DEFAULT NULL COMMENT '法人电话',
  `CUSTOM_LEVEL` varchar(45) NOT NULL COMMENT '客户等级',
  `CUSTOM_NAME` varchar(45) NOT NULL COMMENT '客户名称',
  `IS_VALID` varchar(45) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CUSTOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_CUSTOM_BASIC_INFO
-- ----------------------------
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('3c3478c65dc0403fa68eb0d24c93969b', '马化腾是我小弟', '我对钱没有兴趣', '我是马云', '马爸爸', '888888', '马云', '8888888', 'VVIP', '马云', '1', '2019-08-26 23:52:49');

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
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('3c3478c65dc0403fa68eb0d24c93969b', 'a5b24848e32d47fd9472de4323d61da2');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('3c3478c65dc0403fa68eb0d24c93969b', 'edcb87477e24482da98d0acce3bdb341');

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
  `RECEIVABLE_AMT` decimal(20,5) NOT NULL COMMENT '应收金额',
  `PAYABLE_AMT` decimal(20,5) NOT NULL COMMENT '应付金额',
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_CUSTOM_ORDER_INFO
-- ----------------------------
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('3c3478c65dc0403fa68eb0d24c93969b', '123456', '2019/08/28 21:51:57', '1235.00000', '1235.00000');

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
