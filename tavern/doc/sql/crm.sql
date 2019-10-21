/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-10-22 00:40:21
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
INSERT INTO `CRM_BANK_INFO` VALUES ('33E275BB59FD4377A3B91E7F2A3AD809', '工商银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('4013794EEC7D4367A7846B54078D3211', '中信银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('554BD11CEA5E47DB9580E0F07BEFFE72', '中国银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('61D10B75DEFD40C4B44E312017A37285', '招商银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('74225B1951D54B3E858B5A70DE11C4F6', '建设银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('9ABBBC9F4E434F27BA3DC240CC786AAE', '交通银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('ABDBE742C7D14C378A8794141FAF5775', '光大银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('DD09938B16554B53925EF5CE0FB760D7', '农业银行');
INSERT INTO `CRM_BANK_INFO` VALUES ('E37910CB3CAC4E0DBCA70184817DEF54', '民生银行');

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
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_1', '公积金代缴');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_10', '许可证办理');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_11', '异常业务');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_2', '代缴社保');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_3', '代理记账');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_4', '公司注册');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_5', '公积金代开');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_6', '工商变更');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_7', '公司注销');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_8', '社保代开');
INSERT INTO `CRM_BUSINESS_DICT` VALUES ('BIZ_9', '商标注册');

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
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('1dc1991f5714470bb36d24a533eefb3d', '33E275BB59FD4377A3B91E7F2A3AD809');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('1dc1991f5714470bb36d24a533eefb3d', '61D10B75DEFD40C4B44E312017A37285');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('51df31a548504184a481eb47ea90572d', '74225B1951D54B3E858B5A70DE11C4F6');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('925b956b9029446fafb48ac130ba0202', '61D10B75DEFD40C4B44E312017A37285');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('925b956b9029446fafb48ac130ba0202', '74225B1951D54B3E858B5A70DE11C4F6');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('925b956b9029446fafb48ac130ba0202', 'ABDBE742C7D14C378A8794141FAF5775');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9E93AA78DF7242B18C980650763973C0', '33E275BB59FD4377A3B91E7F2A3AD809');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9E93AA78DF7242B18C980650763973C0', '4013794EEC7D4367A7846B54078D3211');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9E93AA78DF7242B18C980650763973C0', '554BD11CEA5E47DB9580E0F07BEFFE72');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('9E93AA78DF7242B18C980650763973C0', 'ABDBE742C7D14C378A8794141FAF5775');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', '33E275BB59FD4377A3B91E7F2A3AD809');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', '4013794EEC7D4367A7846B54078D3211');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', '554BD11CEA5E47DB9580E0F07BEFFE72');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', '61D10B75DEFD40C4B44E312017A37285');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', '74225B1951D54B3E858B5A70DE11C4F6');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', '9ABBBC9F4E434F27BA3DC240CC786AAE');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', 'ABDBE742C7D14C378A8794141FAF5775');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', 'DD09938B16554B53925EF5CE0FB760D7');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', 'E37910CB3CAC4E0DBCA70184817DEF54');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('c1c09bb1354f49f188cde50591e3c73b', '33E275BB59FD4377A3B91E7F2A3AD809');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('c1c09bb1354f49f188cde50591e3c73b', '4013794EEC7D4367A7846B54078D3211');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('c1c09bb1354f49f188cde50591e3c73b', 'ABDBE742C7D14C378A8794141FAF5775');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('EDCB87477E24482DA98D0ACCE3BDB341', '4013794EEC7D4367A7846B54078D3211');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('EDCB87477E24482DA98D0ACCE3BDB341', '61D10B75DEFD40C4B44E312017A37285');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('EDCB87477E24482DA98D0ACCE3BDB341', '74225B1951D54B3E858B5A70DE11C4F6');
INSERT INTO `CRM_COMPANY_BANK_REL` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', '61D10B75DEFD40C4B44E312017A37285');

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
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('1dc1991f5714470bb36d24a533eefb3d', 'BIZ_4', null, null, '2019-09-25', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('51df31a548504184a481eb47ea90572d', 'BIZ_4', null, null, '2019-10-15', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('925b956b9029446fafb48ac130ba0202', 'BIZ_1', '2019-10-12', '2020-02-12', '2019-10-12', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('925b956b9029446fafb48ac130ba0202', 'BIZ_10', null, null, '2019-10-12', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('925b956b9029446fafb48ac130ba0202', 'BIZ_2', '2019-10-12', '2020-03-12', '2019-10-12', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('925b956b9029446fafb48ac130ba0202', 'BIZ_3', '2019-10-12', '2019-12-12', '2019-10-12', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('925b956b9029446fafb48ac130ba0202', 'BIZ_4', null, null, '2019-10-12', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', 'BIZ_1', '2019-10-13', '2020-10-13', '2019-10-13', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', 'BIZ_4', null, null, '2019-10-13', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', 'BIZ_5', null, null, '2019-10-13', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('ab8a5480b1e14cdc8fa20edfbf635bf9', 'BIZ_7', null, null, '2019-10-17', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('c1c09bb1354f49f188cde50591e3c73b', 'BIZ_10', null, null, '2019-10-13', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('c1c09bb1354f49f188cde50591e3c73b', 'BIZ_4', null, null, '2019-10-13', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('f6c5b44e1d3546439a54528a1e9973c1', 'BIZ_6', null, null, '2019-10-17', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', 'BIZ_1', '2019-10-12', '2020-10-12', '2019-10-12', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', 'BIZ_4', null, null, '2019-10-12', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', 'BIZ_5', null, null, '2019-10-11', '1');
INSERT INTO `CRM_COMPANY_BUSINESS` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', 'BIZ_7', null, null, '2019-10-11', '1');

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
INSERT INTO `CRM_COMPANY_INFO` VALUES ('000d3d9fec0a42c4b33a03127cb73a5b', '上海芙洛妮商贸有限公司', '0', '上海市', '上海市', '浦东新区', '', null, '2019-10-21 10:35:51');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('11fab432862c426680e9f04547d19680', '上海蜗蜗文化传媒有限公司', '1', '上海市', '上海市辖县', '崇明县', '', null, '2019-10-21 11:15:43');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('1dc1991f5714470bb36d24a533eefb3d', '战术大师李楠', '0', '上海市', '上海市辖县', '崇明县', '0', '1.00000', '2019-09-25 11:17:20');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('51df31a548504184a481eb47ea90572d', '上海拓佑财务咨询有限公司', '0', '上海市', '上海市', '徐汇区', '0', '3.00000', '2019-10-15 16:49:04');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('8a6134abc31c49a883c6bd170ac446e3', '上海契克文化传媒有限公司', '0', '上海市', '上海市', '松江区', '', null, '2019-10-21 10:54:24');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('925b956b9029446fafb48ac130ba0202', '抵制NBA', '0', '北京市', '北京市', '东城区', '0', null, '2019-10-12 12:09:59');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('9E93AA78DF7242B18C980650763973C0', '盒马生鲜', '1', '上海市', '上海市', '嘉定区', '1', '12.90000', '2019-08-25 22:03:36');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('a0da57558d8e426aaea792cbfa9d4d07', '上海芙洛妮商贸有限公司', '0', '上海市', '上海市', '浦东新区', '', null, '2019-10-21 10:22:34');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', '阿里妈妈', '0', '海南省', '省直辖县级行政单位', '儋州市', '0', '1111.33400', '2019-08-26 00:13:48');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('ab8a5480b1e14cdc8fa20edfbf635bf9', '上海拓佑财务咨询有限公司', '0', '上海市', '上海市', '杨浦区', '', null, '2019-10-17 15:23:01');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('bb8d8ad9635a4f81a4504fe0b97559ad', '拓佑', '1', '上海市', '上海市', '杨浦区', '', null, '2019-10-17 13:34:10');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('c1c09bb1354f49f188cde50591e3c73b', '无锡高架管理所', '0', '河北省', '秦皇岛市', '青龙满族自治县', '0', null, '2019-10-13 17:52:53');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('EDCB87477E24482DA98D0ACCE3BDB341', '天猫商城', '0', '浙江省', '杭州市', '拱墅区', '1', '2.00000', '2019-08-25 22:07:13');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('f6c5b44e1d3546439a54528a1e9973c1', '上海元升财务咨询有限公司', '0', '上海市', '上海市', '杨浦区', '', null, '2019-10-17 16:02:35');
INSERT INTO `CRM_COMPANY_INFO` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', '中国女排', '0', '山西省', '长治市', '襄垣县', '0', null, '2019-10-10 21:31:08');

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
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('38c675e7ca5a466090d04bdf6af1f772', '朱婷', '', '', '1', '1', '1', '1', '2019-10-10 21:38:36');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('8c3a600f1503422ebca49c8253ef15ba', '姚明', '', '', '', '', '', '', '2019-10-12 12:10:17');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('D16E6BD1E0884CBCA07997BA0E14D80C', '张勇', '23792387598', '32197419847214', '0', '1', '1', '1', '2019-08-26 18:50:40');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('dc73a57af23642b1939569501c652344', '受贿官员1', '', '', '0', '0', '0', '0', '2019-10-13 17:53:59');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('e0c1be8dc1344b97aab48fe89d0a8492', '张常宁', '', '', '1', '1', '1', '1', '2019-10-10 21:38:56');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('e49f84d3ef3946daa135c33ba9ac588a', '郎平', '', '', '0', '0', '0', '0', '2019-10-10 21:39:22');
INSERT INTO `CRM_COMPANY_STAFF_INFO` VALUES ('e7ad647bd0d346c78540894eb3c5b8d0', '卡车超重1', '', '', '1', '1', '1', '1', '2019-10-13 17:53:34');

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
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('925b956b9029446fafb48ac130ba0202', '8c3a600f1503422ebca49c8253ef15ba');
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('A5B24848E32D47FD9472DE4323D61DA2', 'D16E6BD1E0884CBCA07997BA0E14D80C');
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('c1c09bb1354f49f188cde50591e3c73b', 'dc73a57af23642b1939569501c652344');
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('c1c09bb1354f49f188cde50591e3c73b', 'e7ad647bd0d346c78540894eb3c5b8d0');
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', '38c675e7ca5a466090d04bdf6af1f772');
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', 'e0c1be8dc1344b97aab48fe89d0a8492');
INSERT INTO `CRM_COMPANY_STAFF_REL` VALUES ('fc025bd0acf24505814b52fd92bfe7f8', 'e49f84d3ef3946daa135c33ba9ac588a');

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
  `WANGWANG_ACCNT` varchar(45) DEFAULT NULL COMMENT '旺旺账号',
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
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('0209ef0d855e454d85cba38ef5e211c0', '123', '', '', '王浩颖', '13818880421', '', '', '普通客户', '王浩颖', '1', '2019-10-21 10:33:03');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('22442324a48f407e99ffef4a9db290b6', '15921961656', '', 'lb0233', '李奔', '15921961656', '', '', 'VVIP', '李奔', '1', '2019-10-17 16:31:29');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('2de2f55552684c5fa2539bb747d57f66', '18516608340', '', 'cbv2000se1', '陈东', '18516608340', '', '', '普通客户', '陈东', '1', '2019-10-17 14:59:51');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('3C3478C65DC0403FA68EB0D24C93969B', '马化腾是我小弟', '我对钱没有兴趣', '我是马云', '马爸爸', '888888', '张勇', '8888888', 'VVIP', '张勇', '0', '2019-10-15 16:19:57');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('3dacfcefc14f403881ea2b829640afb4', '18847831989', '', '77yu88', '小雨', '18847831989', '', '', '普通客户', '小雨', '0', '2019-10-17 14:58:40');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('41c575e168e94a2ea117fb741f918e2f', '波兰人周琦', '波兰人周琦', '123123132', '波兰人周琦', '13454341321', '周琦', '13454341321', '高级客户', '波兰人周琦', '0', '2019-10-15 16:20:00');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('4f67f6dd69ea485b8c3fcecc15e05f5c', '13916140043', '', '', '刘宝红', '13916140043', '', '', '普通客户', '刘宝红', '1', '2019-10-21 11:11:10');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('5b92ecf6762748ac9f917a0f7d5880a3', '231231231231', '朱元璋', '131231231231', '朱元璋', '1231313412', '朱元璋', '12313123123', '高级客户', '朱元璋', '0', '2019-10-15 16:20:02');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('6713dbe4eb4542039f7694ccb2f905fe', '13671552123', '', '', '王伟', '13671552123', '', '', '普通客户', '王伟', '1', '2019-10-21 11:24:49');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('671644b55d0b4bcd88f6bd2261a55b16', '13813893436', '', 'nj易米', '黄海燕', '13813893436', '', '', '普通客户', '黄海燕', '0', '2019-10-21 10:32:11');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('719f90d5ef0341eb8f493c582df5873b', '18516608340', '', 'cbv2000se1', '陈东', '18516608340', '', '', '普通客户', '陈东', '0', '2019-10-17 14:58:42');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('88197fd653d4494aa77f52fa641db7a0', '豆腐渣高架', '', '豆腐渣高架', '豆腐渣高架', '13213123', '', '', 'VIP', '豆腐渣高架', '0', '2019-10-15 16:38:16');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('9ec47a575da140dabccb3cee9fb4862e', '18616801686', '', 'weni_1125', '熊伟', '18616801686', '', '', '普通客户', '熊伟', '0', '2019-10-17 14:58:43');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('a5a558d6a6f240fcace6f8f2ef59a252', '13818880421', '', '', '王浩颖', '13818880421', '', '', '普通客户', '王浩颖', '0', '2019-10-21 10:46:21');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('b08c01ea64e74c769db1ab377dea3d56', 'luluxiaoye', '', '', '任婷璐', '18084562226', '', '', '普通客户', '任婷璐', '1', '2019-10-21 10:52:27');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('c810cd807a2d45939ff5f1423b4d3ed5', '13917061017', '', 'lznik', '魏晓峰', '13917061017', '', '', '普通客户', '魏晓峰', '0', '2019-10-17 14:58:45');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('d16c2e4721814e08b87668858d63ead9', '123123', '抵制NBA', '抵制NBA', '抵制NBA', '12313123', '', '', 'VIP', '抵制NBA', '0', '2019-10-15 16:38:18');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('eff01578a2164e679d69a115223177da', '13917061017', '', 'lznik', '魏晓峰', '13917061017', '', '', '普通客户', '魏晓峰', '1', '2019-10-17 15:22:28');
INSERT INTO `CRM_CUSTOM_BASIC_INFO` VALUES ('f683a979a2cf453e8c4f8f3837df4ef2', 'cody18022010', 'Cody', '', '寇新江', '13585563858', 'cody_kou', '', '普通客户', '寇倍源', '1', '2019-10-18 15:25:14');

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
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('3C3478C65DC0403FA68EB0D24C93969B', 'A5B24848E32D47FD9472DE4323D61DA2');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('3C3478C65DC0403FA68EB0D24C93969B', 'EDCB87477E24482DA98D0ACCE3BDB341');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('3dacfcefc14f403881ea2b829640afb4', '51df31a548504184a481eb47ea90572d');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('41c575e168e94a2ea117fb741f918e2f', '1dc1991f5714470bb36d24a533eefb3d');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('4f67f6dd69ea485b8c3fcecc15e05f5c', '11fab432862c426680e9f04547d19680');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('5b92ecf6762748ac9f917a0f7d5880a3', 'fc025bd0acf24505814b52fd92bfe7f8');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('671644b55d0b4bcd88f6bd2261a55b16', 'f6c5b44e1d3546439a54528a1e9973c1');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('88197fd653d4494aa77f52fa641db7a0', 'c1c09bb1354f49f188cde50591e3c73b');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('a5a558d6a6f240fcace6f8f2ef59a252', '000d3d9fec0a42c4b33a03127cb73a5b');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('b08c01ea64e74c769db1ab377dea3d56', '8a6134abc31c49a883c6bd170ac446e3');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('c810cd807a2d45939ff5f1423b4d3ed5', 'bb8d8ad9635a4f81a4504fe0b97559ad');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('d16c2e4721814e08b87668858d63ead9', '925b956b9029446fafb48ac130ba0202');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('eff01578a2164e679d69a115223177da', 'ab8a5480b1e14cdc8fa20edfbf635bf9');
INSERT INTO `CRM_CUSTOM_COMPANY_REL` VALUES ('f683a979a2cf453e8c4f8f3837df4ef2', 'a0da57558d8e426aaea792cbfa9d4d07');

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
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_SOURCE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ORDER_ID` (`ORDER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_CUSTOM_ORDER_INFO
-- ----------------------------
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('3C3478C65DC0403FA68EB0D24C93969B', '0001', '2019/08/09 16:00:07', '21190', '21190', '1', null);
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('3C3478C65DC0403FA68EB0D24C93969B', '0002', '2019/08/28 00:00:00', '156465', '464654', '2', null);
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('22442324a48f407e99ffef4a9db290b6', '111', '2019/10/17 16:32:15', '1900', '1800', '3', '烁翼旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('6713dbe4eb4542039f7694ccb2f905fe', '123', '2019/10/19 00:00:00', '3500', '3500', '4', '烁翼旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('d16c2e4721814e08b87668858d63ead9', '1231231', '2019/10/12 12:10:36', '123', '131', '5', null);
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('b08c01ea64e74c769db1ab377dea3d56', '123334', '2019/10/18 00:00:00', '770', '760', '6', '支付宝');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('5b92ecf6762748ac9f917a0f7d5880a3', '12431431', '2019/10/01 00:00:00', '180', '100', '7', null);
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('41c575e168e94a2ea117fb741f918e2f', '13213123', '2019/09/25 11:14:15', '1000', '1000', '8', null);
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('a5a558d6a6f240fcace6f8f2ef59a252', '20191018200040011100120072265912', '2019/10/18 00:00:00', '300', '300', '9', '支付宝');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('88197fd653d4494aa77f52fa641db7a0', '21313123', '2019/10/13 17:54:18', '180', '190', '10', null);
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('b08c01ea64e74c769db1ab377dea3d56', '22', '2019/10/18 00:00:00', '600', '600', '11', '支付宝');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('b08c01ea64e74c769db1ab377dea3d56', '2222', '2019/10/18 00:00:00', '770', '770', '12', '支付宝');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('3dacfcefc14f403881ea2b829640afb4', '354235', '2019/10/15 23:57:17', '1234', '1233', '14', '咔嗒旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('3dacfcefc14f403881ea2b829640afb4', '665318627173668583', '2019/10/15 13:03:43', '2400', '2400', '15', null);
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('2de2f55552684c5fa2539bb747d57f66', '666990754408756642', '2019/10/16 15:49:06', '2400', '2400', '16', '烁翼旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('eff01578a2164e679d69a115223177da', '667046112124285730', '2019/10/16 18:49:00', '1500', '1500', '17', '咔嗒旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('9ec47a575da140dabccb3cee9fb4862e', '667170304274853824', '2019/10/16 00:00:00', '3200', '3200', '18', '咔嗒旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('eff01578a2164e679d69a115223177da', '667958912205285730', '2019/10/17 13:13:23', '3000', '3000', '19', '咔嗒旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('f683a979a2cf453e8c4f8f3837df4ef2', '669671938803109353', '2019/10/18 00:00:00', '600', '600', '20', '烁翼旗舰店');
INSERT INTO `CRM_CUSTOM_ORDER_INFO` VALUES ('671644b55d0b4bcd88f6bd2261a55b16', '对公账号转入', '2019/10/17 15:59:45', '2500', '2500', '21', '烁翼旗舰店');

-- ----------------------------
-- Table structure for CRM_CUSTOM_THIRD_PARTY_INFO
-- ----------------------------
DROP TABLE IF EXISTS `CRM_CUSTOM_THIRD_PARTY_INFO`;
CREATE TABLE `CRM_CUSTOM_THIRD_PARTY_INFO` (
  `THIRD_PARTY_ID` varchar(45) NOT NULL,
  `THIRD_PARTY_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`THIRD_PARTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_CUSTOM_THIRD_PARTY_INFO
-- ----------------------------
INSERT INTO `CRM_CUSTOM_THIRD_PARTY_INFO` VALUES ('1', '华为技术有限公司');
INSERT INTO `CRM_CUSTOM_THIRD_PARTY_INFO` VALUES ('2', '小米股份有限公司');
INSERT INTO `CRM_CUSTOM_THIRD_PARTY_INFO` VALUES ('3', '苹果股份有限公司');
INSERT INTO `CRM_CUSTOM_THIRD_PARTY_INFO` VALUES ('4', '阿里巴巴股份有限公司');

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
  `CREATE_DATE` datetime DEFAULT NULL,
  `OWNER` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CRM_ORDER_BUSINESS_REL
-- ----------------------------
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('09b1660e997c42ce8e6b8a38af0b96d7', '10', 'BIZ_4', 'c1c09bb1354f49f188cde50591e3c73b', 'a286d363052946d5a88b5e824c590540', '0', '', null, '加急', null, '', '2019-10-13 17:56:55', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('139eea231b1b48b3bccdd4b340584ad2', '10', 'BIZ_1', 'c1c09bb1354f49f188cde50591e3c73b', 'a286d363052946d5a88b5e824c590540', '1', '', null, '加急', null, '', '2019-10-13 17:56:16', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', '16', 'BIZ_4', '', '3d2c42b03ead49dba7d65ddf1f854428', '0', '', null, '一般', null, '烁翼付款 嘉定小规模套餐 旺旺：cbv2000se1 陈东,18516608340 我直接拉群', '2019-10-17 15:02:05', '陈峰');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('4615732a21f9446f9fcec8b9bd4fc8bd', '1', 'BIZ_5', 'A5B24848E32D47FD9472DE4323D61DA2', 'a286d363052946d5a88b5e824c590540', '1', '1', '1424', '加急', null, '1414', '2019-10-13 20:45:57', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('48ec0b235eed4c49868570959b95507f', '21', 'BIZ_6', 'f6c5b44e1d3546439a54528a1e9973c1', 'e31758ba68804628aff180ceb271650b', '0', '', null, '一般', null, '', '2019-10-17 16:03:08', '钮思傲');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('5398e437b12f4461b3b6d692e91d649a', '14', 'BIZ_4', '', 'a286d363052946d5a88b5e824c590540', '1', '1', '1233', '加急', null, '测试：公司注册，挺着急的', '2019-10-16 23:10:02', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('5b73e26c064f4e5db174cfd515952852', '7', 'BIZ_4', 'fc025bd0acf24505814b52fd92bfe7f8', 'a286d363052946d5a88b5e824c590540', '1', '1', '23', '加急', null, '2rfwef', '2019-10-12 10:36:15', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('65792f61524048d393a96fc6a1311d37', '7', 'BIZ_1', 'fc025bd0acf24505814b52fd92bfe7f8', 'a286d363052946d5a88b5e824c590540', '1', '1', '12', '加急', null, 'sdfsa', '2019-10-12 10:48:32', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('6ccaec607d5443fba4628a34f3f66ca2', '20', 'BIZ_9', '', '167390cb434c4d539c9ba1760ba1e901', '1', '', null, '一般', null, '老客户推荐', '2019-10-18 15:29:33', '魏稳根');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('6f917efa7d414ef9b7bbc4ea385259fe', '1', 'BIZ_4', 'A5B24848E32D47FD9472DE4323D61DA2', 'a286d363052946d5a88b5e824c590540', '1', '1', '12', '加急', null, '公司注册，请立即执行', '2019-10-13 23:52:21', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('74149bd10aef457d9f2ce1a7e3f28077', '7', 'BIZ_7', 'fc025bd0acf24505814b52fd92bfe7f8', '42ed651d283b4019aaab51117063b5f1', '1', '', null, '加急', null, '', '2019-10-11 22:15:04', '我是外勤2');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('7b1edf23d02d4f0185439ae87b4416b8', '5', 'BIZ_2', '925b956b9029446fafb48ac130ba0202', 'a286d363052946d5a88b5e824c590540', '1', '2', '1231', '加急', null, '萨芬士大夫', '2019-10-12 12:15:18', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('80707a12ccd84ebeb98da4a4f8c00786', '11', 'BIZ_10', '', '3d2c42b03ead49dba7d65ddf1f854428', '1', '', null, '一般', null, '危化品', '2019-10-21 11:27:33', '陈峰');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('8681a7eff5fc42b68bd99b12df110843', '5', 'BIZ_4', '925b956b9029446fafb48ac130ba0202', 'a286d363052946d5a88b5e824c590540', '0', '', null, '一般', null, '', '2019-10-12 12:17:59', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('8f6d74904e4a43899b0119766a1c2f5f', '10', 'BIZ_10', 'c1c09bb1354f49f188cde50591e3c73b', 'a286d363052946d5a88b5e824c590540', '1', '1', '213', '加急', null, '许可证办理', '2019-10-13 19:06:01', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', '15', 'BIZ_4', '51df31a548504184a481eb47ea90572d', 'a286d363052946d5a88b5e824c590540', '0', '', null, '一般', null, '', '2019-10-15 16:50:23', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('b1533c294e424deaaba752d9621d3971', '5', 'BIZ_1', '925b956b9029446fafb48ac130ba0202', 'a286d363052946d5a88b5e824c590540', '1', '1', null, '加急', null, '', '2019-10-12 12:14:03', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('b3286a15d4604d73b64a636307cb9e6c', '7', 'BIZ_4', 'fc025bd0acf24505814b52fd92bfe7f8', 'a286d363052946d5a88b5e824c590540', '1', '', null, '加急', null, '', '2019-10-11 22:14:46', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('b3dd4297235248538339a475164f5dfa', '1', 'BIZ_1', 'A5B24848E32D47FD9472DE4323D61DA2', 'a286d363052946d5a88b5e824c590540', '1', '1', '123', '加急', null, '公积金代缴，请立即执行', '2019-10-13 23:47:45', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('c70378dc915742dca8fd5b77125b5159', '18', 'BIZ_4', '', 'a286d363052946d5a88b5e824c590540', '0', '', null, '一般', null, '', '2019-10-17 13:09:03', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('d6c6d507a8e34d159af5a9342a2b5ea0', '3', 'BIZ_11', '', '24f1228d56b743b399e1f8d4ec70fc67', '0', '', null, '加急', null, '异常业务测试：挺着急的', '2019-10-20 21:34:11', '田国庆');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('d74393ec477d40f788ef308f0189c246', '5', 'BIZ_10', '925b956b9029446fafb48ac130ba0202', 'a286d363052946d5a88b5e824c590540', '0', '', null, '一般', null, '阿萨法发', '2019-10-12 12:14:28', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('e2fb75f5b0674b139c34191384a987ef', '8', 'BIZ_4', '1dc1991f5714470bb36d24a533eefb3d', 'a286d363052946d5a88b5e824c590540', '1', '1', '500', '一般', null, '', '2019-09-25 11:25:02', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('e3e2fc2b51c74fca8e4ca228874e3344', '3', 'BIZ_11', '', '15902a94b57648549216a8f2ed0765d7', '0', '', null, '加急', null, '异常业务测试：你看着办吧', '2019-10-20 21:49:45', '张强强');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('e80c2efa21c045fa9d275722372fc238', '19', 'BIZ_7', 'ab8a5480b1e14cdc8fa20edfbf635bf9', '2869b57d6832449c916dbbb4e2128cac', '0', '', null, '一般', null, '', '2019-10-17 15:48:45', '郑安心');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('ea47292a2b3f436fa38650e6b496a367', '7', 'BIZ_5', 'fc025bd0acf24505814b52fd92bfe7f8', 'a286d363052946d5a88b5e824c590540', '0', '', null, '一般', null, '', '2019-10-11 22:13:34', '我是售后_1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('ebcf732e7612463d8377f210bc4dac32', '5', 'BIZ_3', '925b956b9029446fafb48ac130ba0202', '4', '0', '', null, '一般', null, '', '2019-10-12 12:16:50', '我是财务主管1');
INSERT INTO `CRM_ORDER_BUSINESS_REL` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', '3', 'BIZ_4', '', '3d2c42b03ead49dba7d65ddf1f854428', '0', '', null, '一般', null, '崇明小规模套餐', '2019-10-17 16:35:33', '陈峰');

-- ----------------------------
-- Table structure for CRM_ORDER_DLJZ_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_ORDER_DLJZ_DETAIL`;
CREATE TABLE `CRM_ORDER_DLJZ_DETAIL` (
  `EVENT_ID` varchar(45) NOT NULL,
  `ORDER_ID` varchar(45) NOT NULL,
  `BEGIN_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `IS_BEGIN` varchar(1) DEFAULT NULL COMMENT '服务是否开始',
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理记账明细表';

-- ----------------------------
-- Records of CRM_ORDER_DLJZ_DETAIL
-- ----------------------------
INSERT INTO `CRM_ORDER_DLJZ_DETAIL` VALUES ('ebcf732e7612463d8377f210bc4dac32', '5', '2019-10-12', '2019-12-12', '1');

-- ----------------------------
-- Table structure for CRM_ORDER_GJJSBDJ_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `CRM_ORDER_GJJSBDJ_DETAIL`;
CREATE TABLE `CRM_ORDER_GJJSBDJ_DETAIL` (
  `EVENT_ID` varchar(45) NOT NULL,
  `ORDER_ID` varchar(45) NOT NULL,
  `BUSINESS_ID` varchar(45) DEFAULT NULL,
  `BEGIN_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `EMPLOYEE_NUM` int(11) DEFAULT NULL COMMENT '缴款人数',
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公积金社保代缴明细表';

-- ----------------------------
-- Records of CRM_ORDER_GJJSBDJ_DETAIL
-- ----------------------------
INSERT INTO `CRM_ORDER_GJJSBDJ_DETAIL` VALUES ('139eea231b1b48b3bccdd4b340584ad2', '10', 'BIZ_1', '2019-10-13', '2019-10-13', '0');
INSERT INTO `CRM_ORDER_GJJSBDJ_DETAIL` VALUES ('65792f61524048d393a96fc6a1311d37', '7', 'BIZ_1', '2019-10-12', '2019-10-12', '0');
INSERT INTO `CRM_ORDER_GJJSBDJ_DETAIL` VALUES ('7b1edf23d02d4f0185439ae87b4416b8', '5', 'BIZ_2', '2019-10-12', '2019-10-12', '0');
INSERT INTO `CRM_ORDER_GJJSBDJ_DETAIL` VALUES ('b1533c294e424deaaba752d9621d3971', '5', 'BIZ_1', '2019-10-12', '2019-10-12', '1');
INSERT INTO `CRM_ORDER_GJJSBDJ_DETAIL` VALUES ('b3dd4297235248538339a475164f5dfa', '1', 'BIZ_1', '2019-10-13', '2019-10-13', '0');

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
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('09b1660e997c42ce8e6b8a38af0b96d7', '10', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', '16', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('5398e437b12f4461b3b6d692e91d649a', '14', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('5b73e26c064f4e5db174cfd515952852', '7', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('6f917efa7d414ef9b7bbc4ea385259fe', '1', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('8681a7eff5fc42b68bd99b12df110843', '5', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', '15', '0', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('b3286a15d4604d73b64a636307cb9e6c', '7', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('c70378dc915742dca8fd5b77125b5159', '18', '1', '虚拟注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('e2fb75f5b0674b139c34191384a987ef', '8', '1', '实地注册');
INSERT INTO `CRM_ORDER_GSZC_DETAIL` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', '3', '1', '虚拟注册');
