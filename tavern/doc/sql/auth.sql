/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-09-19 02:01:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for AUTH_MENU
-- ----------------------------
DROP TABLE IF EXISTS `AUTH_MENU`;
CREATE TABLE `AUTH_MENU` (
  `MENU_ID` bigint(45) NOT NULL AUTO_INCREMENT,
  `MENU_URL` varchar(45) DEFAULT NULL,
  `MENU_NAME` varchar(45) DEFAULT NULL,
  `PARENT_ID` bigint(45) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `PERMS` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `TYPE` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `ICON` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `ORDER_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AUTH_MENU
-- ----------------------------
INSERT INTO `AUTH_MENU` VALUES ('1', '', '系统管理', '0', '', '0', 'el-icon-setting', '1', '2019-09-17 22:15:54');
INSERT INTO `AUTH_MENU` VALUES ('2', '', '票据管理', '0', '', '0', 'el-icon-message', '2', '2019-09-17 22:37:42');
INSERT INTO `AUTH_MENU` VALUES ('3', '', '银行流水', '2', '', '0', 'el-icon-bank-card', '3', '2019-09-18 23:26:31');
INSERT INTO `AUTH_MENU` VALUES ('4', '/bank/upload', '流水上传', '3', '', '1', 'el-icon-upload', '4', '2019-09-17 22:42:42');
INSERT INTO `AUTH_MENU` VALUES ('5', '/bank/query', '流水查询', '3', '', '1', 'el-icon-search', '5', '2019-09-17 22:43:27');
INSERT INTO `AUTH_MENU` VALUES ('6', '', '发票清单', '2', '', '0', 'el-icon-tickets', '6', '2019-09-18 23:26:48');
INSERT INTO `AUTH_MENU` VALUES ('7', '/invoice/upload', '发票上传', '6', '', '1', 'el-icon-upload', '7', '2019-09-17 22:53:41');
INSERT INTO `AUTH_MENU` VALUES ('8', '/sys/menu', '菜单管理', '1', '', '1', 'el-icon-menu', '8', '2019-09-17 22:56:00');
INSERT INTO `AUTH_MENU` VALUES ('9', '/invoice/query', '发票查询', '6', '', '1', 'el-icon-search', '9', '2019-09-18 23:17:28');
INSERT INTO `AUTH_MENU` VALUES ('10', '/zzs/upload', '增值税', '2', '', '1', 'el-icon-money', '10', '2019-09-18 23:18:03');
INSERT INTO `AUTH_MENU` VALUES ('11', '/extraInvoice/query', '专票补票(其它票据)', '2', '', '1', 'el-icon-document-copy', '11', '2019-09-18 23:18:41');
INSERT INTO `AUTH_MENU` VALUES ('12', '', '工资单', '2', '', '0', 'el-icon-document-copy', '12', '2019-09-18 23:26:55');
INSERT INTO `AUTH_MENU` VALUES ('13', '/salary/upload', '工资上传', '12', '', '1', 'el-icon-upload', '13', '2019-09-18 23:20:14');
INSERT INTO `AUTH_MENU` VALUES ('14', '/salary/query', '工资查询', '12', '', '1', 'el-icon-search', '14', '2019-09-18 23:22:31');
INSERT INTO `AUTH_MENU` VALUES ('15', '', '客户登记', '0', '', '0', 'el-icon-s-custom', '15', '2019-09-18 23:26:02');
INSERT INTO `AUTH_MENU` VALUES ('16', '/preSales/customer', '客户信息', '15', '', '1', 'el-icon-user-solid', '16', '2019-09-18 23:24:41');
INSERT INTO `AUTH_MENU` VALUES ('17', '', '外勤协办', '0', '', '0', 'el-icon-user-solid', '17', '2019-09-18 23:32:28');
INSERT INTO `AUTH_MENU` VALUES ('18', '/outWorker/outerPending', '外勤待办', '17', '', '1', 'el-icon-user-solid', '19', '2019-09-18 23:27:42');
INSERT INTO `AUTH_MENU` VALUES ('19', '', '售后服务', '0', '', '0', 'el-icon-s-custom', '20', '2019-09-18 23:28:15');
INSERT INTO `AUTH_MENU` VALUES ('20', '/AfterSales/afterSalesPending', '售后待办', '19', '', '1', 'el-icon-user-solid', '21', '2019-09-18 23:28:51');
INSERT INTO `AUTH_MENU` VALUES ('21', '', '出纳服务', '0', '', '0', 'el-icon-s-custom', '22', '2019-09-18 23:29:17');
INSERT INTO `AUTH_MENU` VALUES ('22', '/Cashier/cashierPending', '出纳待办', '21', '', '1', 'el-icon-user-solid', '23', '2019-09-18 23:29:59');
INSERT INTO `AUTH_MENU` VALUES ('23', '', '主管服务', '0', '', '0', 'el-icon-s-custom', '24', '2019-09-18 23:30:26');
INSERT INTO `AUTH_MENU` VALUES ('24', '/Director/directorPending', '主管待办', '23', '', '1', 'el-icon-user-solid', '27', '2019-09-18 23:31:22');
INSERT INTO `AUTH_MENU` VALUES ('25', '/Director/bizQuery', '业务办理进度查询', '23', '', '1', 'el-icon-time', '28', '2019-09-18 23:32:07');
INSERT INTO `AUTH_MENU` VALUES ('26', '/sys/user', '用户管理', '1', '', '1', 'el-icon-service', '30', '2019-09-19 00:36:48');
INSERT INTO `AUTH_MENU` VALUES ('27', '/sys/role', '角色管理', '1', '', '1', 'el-icon-view', '31', '2019-09-19 00:37:23');

-- ----------------------------
-- Table structure for AUTH_MENU_ROLE_REL
-- ----------------------------
DROP TABLE IF EXISTS `AUTH_MENU_ROLE_REL`;
CREATE TABLE `AUTH_MENU_ROLE_REL` (
  `MENU_ID` bigint(45) NOT NULL,
  `ROLE_ID` varchar(45) NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`MENU_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AUTH_MENU_ROLE_REL
-- ----------------------------
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('1', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('1', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('2', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('2', 'R_SCZG', '2019-09-18 15:18:03');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('2', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'R_SCZG', '2019-09-18 15:18:03');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'R_SCZG', '2019-09-18 15:18:03');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'R_SCZG', '2019-09-18 15:18:03');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('6', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('6', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('7', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('7', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('8', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('8', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('9', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('9', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('10', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('10', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('11', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('11', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('12', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('12', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('13', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('13', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('14', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('14', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('15', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('15', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('16', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('16', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('17', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('17', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('18', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('18', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('19', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('19', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('20', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('20', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('21', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('21', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('22', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('22', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('24', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('24', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('25', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('25', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('26', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('27', 'R_TEST', '2019-09-19 00:38:23');
