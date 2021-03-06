/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-10-22 00:40:31
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
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

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
INSERT INTO `AUTH_MENU` VALUES ('28', '', '编辑', '8', 'sys:menu:edit', '2', '', '0', '2019-09-21 23:06:55');
INSERT INTO `AUTH_MENU` VALUES ('29', '', '删除', '8', 'sys:menu:del', '2', '', '0', '2019-09-21 23:07:15');
INSERT INTO `AUTH_MENU` VALUES ('30', '', '新增', '8', 'sys:menu:add', '2', '', '0', '2019-09-21 23:07:36');
INSERT INTO `AUTH_MENU` VALUES ('31', '', '查询', '8', 'sys:menu:view', '2', '', '0', '2019-09-21 23:07:59');
INSERT INTO `AUTH_MENU` VALUES ('32', '', '编辑', '26', 'sys:user:edit', '2', '', '0', '2019-09-21 23:11:20');
INSERT INTO `AUTH_MENU` VALUES ('33', '', '删除', '26', 'sys:user:del', '2', '', '0', '2019-09-21 23:11:40');
INSERT INTO `AUTH_MENU` VALUES ('36', '', '新增', '26', 'sys:user:add', '2', '', '0', '2019-09-21 23:24:14');
INSERT INTO `AUTH_MENU` VALUES ('37', '', '查询', '26', 'sys:user:view', '2', '', '0', '2019-09-21 23:24:39');
INSERT INTO `AUTH_MENU` VALUES ('38', '', '新增', '27', 'sys:role:add', '2', '', '0', '2019-09-21 23:25:01');
INSERT INTO `AUTH_MENU` VALUES ('39', '', '编辑', '27', 'sys:role:edit', '2', '', '0', '2019-09-21 23:25:18');
INSERT INTO `AUTH_MENU` VALUES ('40', '', '删除', '27', 'sys:role:del', '2', '', '0', '2019-09-21 23:26:09');
INSERT INTO `AUTH_MENU` VALUES ('41', '', '查询', '27', 'sys:role:view', '2', '', '0', '2019-09-21 23:26:28');
INSERT INTO `AUTH_MENU` VALUES ('42', '', '查询', '4', 'sys:bank:upload:view', '2', '', '0', '2019-09-22 00:14:04');
INSERT INTO `AUTH_MENU` VALUES ('43', '', '上传', '4', 'sys:bank:upload', '2', '', '0', '2019-09-22 00:15:49');
INSERT INTO `AUTH_MENU` VALUES ('44', '', '删除', '4', 'sys:bank:upload:del', '2', '', '0', '2019-09-22 00:16:09');
INSERT INTO `AUTH_MENU` VALUES ('45', '', '删除', '5', 'sys:bank:query:del', '2', '', '0', '2019-09-22 00:22:59');
INSERT INTO `AUTH_MENU` VALUES ('46', '', '查询', '5', 'sys:bank:query:view', '2', '', '0', '2019-09-22 00:23:22');
INSERT INTO `AUTH_MENU` VALUES ('48', '', '查询', '5', 'sys:bank:dtl:view', '2', '', '0', '2019-09-22 13:22:14');
INSERT INTO `AUTH_MENU` VALUES ('49', '', '查询', '7', 'sys:invoice:upload:view', '2', '', '0', '2019-09-22 19:10:46');
INSERT INTO `AUTH_MENU` VALUES ('50', '', '上传', '7', 'sys:invoice:upload', '2', '', '0', '2019-09-22 19:11:08');
INSERT INTO `AUTH_MENU` VALUES ('51', '', '删除', '7', 'sys:invoice:upload:del', '2', '', '0', '2019-09-22 19:11:29');
INSERT INTO `AUTH_MENU` VALUES ('52', '', '查询', '9', 'sys:invoice:query:view', '2', '', '0', '2019-09-22 20:14:06');
INSERT INTO `AUTH_MENU` VALUES ('53', '', '删除', '9', 'sys:invoice:query:del', '2', '', '0', '2019-09-22 20:14:26');
INSERT INTO `AUTH_MENU` VALUES ('54', '', '查询', '10', 'sys:zzs:upload:view', '2', '', '0', '2019-09-22 21:27:37');
INSERT INTO `AUTH_MENU` VALUES ('55', '', '上传', '10', 'sys:zzs:upload', '2', '', '0', '2019-09-22 21:29:32');
INSERT INTO `AUTH_MENU` VALUES ('56', '', '删除', '10', 'sys:zzs:upload:del', '2', '', '0', '2019-09-22 21:29:49');
INSERT INTO `AUTH_MENU` VALUES ('57', '', '查询', '10', 'sys:zzs:dtl:view', '2', '', '0', '2019-09-22 21:38:04');
INSERT INTO `AUTH_MENU` VALUES ('58', '', '提交', '10', 'sys:zzs:amend:submit', '2', '', '0', '2019-09-22 23:25:45');
INSERT INTO `AUTH_MENU` VALUES ('59', '', '新增', '11', 'sys:extInvoice:add', '2', '', '0', '2019-09-23 00:00:31');
INSERT INTO `AUTH_MENU` VALUES ('60', '', '编辑', '11', 'sys:extInvoice:edit', '2', '', '0', '2019-09-23 00:00:50');
INSERT INTO `AUTH_MENU` VALUES ('61', '', '查询', '11', 'sys:extInvoice:view', '2', '', '0', '2019-09-23 00:01:10');
INSERT INTO `AUTH_MENU` VALUES ('62', '', '删除', '11', 'sys:extInvoice:del', '2', '', '0', '2019-09-23 00:01:25');
INSERT INTO `AUTH_MENU` VALUES ('63', '', '查询', '13', 'sys:salary:upload:view', '2', '', '0', '2019-09-23 00:10:25');
INSERT INTO `AUTH_MENU` VALUES ('64', '', '上传', '13', 'sys:salary:upload', '2', '', '0', '2019-09-23 00:10:42');
INSERT INTO `AUTH_MENU` VALUES ('65', '', '删除', '13', 'sys:salary:upload:del', '2', '', '0', '2019-09-23 00:11:01');
INSERT INTO `AUTH_MENU` VALUES ('66', '', '删除', '14', 'sys:salary:query:del', '2', '', '0', '2019-09-23 00:19:00');
INSERT INTO `AUTH_MENU` VALUES ('67', '', '查询', '14', 'sys:salary:query:view', '2', '', '0', '2019-09-23 00:19:18');
INSERT INTO `AUTH_MENU` VALUES ('68', '', '删除', '16', 'sys:presales:customer:del', '2', '', '0', '2019-09-23 00:30:49');
INSERT INTO `AUTH_MENU` VALUES ('69', '', '查询', '16', 'sys:presales:customer:view', '2', '', '0', '2019-09-23 00:31:07');
INSERT INTO `AUTH_MENU` VALUES ('70', '', '新增', '16', 'sys:presales:customer:add', '2', '', '0', '2019-09-23 00:31:22');
INSERT INTO `AUTH_MENU` VALUES ('71', '', '编辑', '16', 'sys:presales:customer:edit', '2', '', '0', '2019-09-23 00:31:37');
INSERT INTO `AUTH_MENU` VALUES ('72', '', '客户详情', '16', 'sys:presales:customer:dtl', '2', '', '0', '2019-09-23 00:32:31');
INSERT INTO `AUTH_MENU` VALUES ('73', '', '客户订单', '16', 'sys:presales:customer:order', '2', '', '0', '2019-09-23 00:32:56');
INSERT INTO `AUTH_MENU` VALUES ('74', '', '新增公司信息', '16', 'sys:presales:customer:dtl:add', '2', '', '0', '2019-09-23 22:15:52');
INSERT INTO `AUTH_MENU` VALUES ('75', '', '修改公司信息', '16', 'sys:presales:customer:dtl:edit', '2', '', '0', '2019-09-23 22:16:12');
INSERT INTO `AUTH_MENU` VALUES ('77', '', '公司信息详情', '16', 'sys:presales:customer:dtl:view', '2', '', '0', '2019-09-23 22:17:28');
INSERT INTO `AUTH_MENU` VALUES ('78', '', '删除', '16', 'sys:presales:customer:dtl:del', '2', '', '0', '2019-09-23 22:17:57');
INSERT INTO `AUTH_MENU` VALUES ('79', '', '新增员工', '16', 'sys:presales:company:staff:add', '2', '', '0', '2019-09-23 22:49:00');
INSERT INTO `AUTH_MENU` VALUES ('80', '', '修改员工', '16', 'sys:presales:company:staff:edit', '2', '', '0', '2019-09-23 22:49:38');
INSERT INTO `AUTH_MENU` VALUES ('81', '', '删除', '16', 'sys:presales:company:staff:del', '2', '', '0', '2019-09-23 22:49:59');
INSERT INTO `AUTH_MENU` VALUES ('82', '', '修改订单', '16', 'sys:presales:order:edit', '2', '', '0', '2019-09-23 23:03:16');
INSERT INTO `AUTH_MENU` VALUES ('83', '', '订单详情', '16', 'sys:presales:order:dtl', '2', '', '0', '2019-09-23 23:03:35');
INSERT INTO `AUTH_MENU` VALUES ('84', '', '查询', '16', 'sys:presales:order:view', '2', '', '0', '2019-09-23 23:03:52');
INSERT INTO `AUTH_MENU` VALUES ('85', '', '新增', '16', 'sys:presales:order:add', '2', '', '0', '2019-09-23 23:04:15');
INSERT INTO `AUTH_MENU` VALUES ('86', '', '添加业务', '16', 'sys:presales:business:add', '2', '', '0', '2019-09-23 23:07:08');
INSERT INTO `AUTH_MENU` VALUES ('87', '', '查询', '18', 'sys:outer:pending:view', '2', '', '0', '2019-09-23 23:44:22');
INSERT INTO `AUTH_MENU` VALUES ('88', '', '流程日志', '18', 'sys:outer:pending:flow', '2', '', '0', '2019-09-23 23:44:43');
INSERT INTO `AUTH_MENU` VALUES ('89', '', '添加备注', '18', 'sys:outer:pending:addlog', '2', '', '0', '2019-09-23 23:45:04');
INSERT INTO `AUTH_MENU` VALUES ('90', '', '退款审批', '18', 'sys:outer:pending:drawback', '2', '', '0', '2019-09-23 23:45:23');
INSERT INTO `AUTH_MENU` VALUES ('91', '', '下一步', '18', 'sys:outer:pending:next', '2', '', '0', '2019-09-23 23:45:41');
INSERT INTO `AUTH_MENU` VALUES ('92', '', '查询', '20', 'sys:aftersales:pending:view', '2', '', '0', '2019-09-23 23:48:48');
INSERT INTO `AUTH_MENU` VALUES ('93', '', '流程日志', '20', 'sys:aftersales:pending:flow', '2', '', '0', '2019-09-23 23:49:09');
INSERT INTO `AUTH_MENU` VALUES ('94', '', '添加备注', '20', 'sys:aftersales:pending:addlog', '2', '', '0', '2019-09-23 23:49:29');
INSERT INTO `AUTH_MENU` VALUES ('95', '', '退款审批', '20', 'sys:aftersales:pending:drawback', '2', '', '0', '2019-09-23 23:49:53');
INSERT INTO `AUTH_MENU` VALUES ('96', '', '下一步', '20', 'sys:aftersales:pending:next', '2', '', '0', '2019-09-23 23:50:10');
INSERT INTO `AUTH_MENU` VALUES ('98', '', '查询', '22', 'sys:cashier:pending:view', '2', '', '0', '2019-09-23 23:52:44');
INSERT INTO `AUTH_MENU` VALUES ('99', '', '流程日志', '22', 'sys:cashier:pending:flow', '2', '', '0', '2019-09-23 23:53:04');
INSERT INTO `AUTH_MENU` VALUES ('100', '', '添加备注', '22', 'sys:cashier:pending:addlog', '2', '', '0', '2019-09-23 23:53:21');
INSERT INTO `AUTH_MENU` VALUES ('101', '', '退款审批', '22', 'sys:cashier:pending:drawback', '2', '', '0', '2019-09-23 23:53:41');
INSERT INTO `AUTH_MENU` VALUES ('102', '', '下一步', '22', 'sys:cashier:pending:next', '2', '', '0', '2019-09-23 23:53:58');
INSERT INTO `AUTH_MENU` VALUES ('103', '', '查询', '24', 'sys:director:pending:view', '2', '', '0', '2019-09-23 23:56:17');
INSERT INTO `AUTH_MENU` VALUES ('104', '', '流程日志', '24', 'sys:director:pending:flow', '2', '', '0', '2019-09-23 23:56:35');
INSERT INTO `AUTH_MENU` VALUES ('105', '', '添加备注', '24', 'sys:director:pending:addlog', '2', '', '0', '2019-09-23 23:56:50');
INSERT INTO `AUTH_MENU` VALUES ('106', '', '退款审批', '24', 'sys:director:pending:drawback', '2', '', '0', '2019-09-23 23:57:11');
INSERT INTO `AUTH_MENU` VALUES ('107', '', '下一步', '24', 'sys:director:pending:next', '2', '', '0', '2019-09-23 23:57:30');
INSERT INTO `AUTH_MENU` VALUES ('108', '', '查询', '25', 'sys:director:biz:view', '2', '', '0', '2019-09-24 00:12:16');
INSERT INTO `AUTH_MENU` VALUES ('109', '', '转授权', '25', 'sys:director:biz:rechoose', '2', '', '0', '2019-09-24 00:13:01');
INSERT INTO `AUTH_MENU` VALUES ('110', '', '流程日志', '25', 'sys:director:biz:flow', '2', '', '0', '2019-09-24 00:13:19');
INSERT INTO `AUTH_MENU` VALUES ('111', '', '公司详情', '25', 'sys:director:biz:company:view', '2', '', '0', '2019-09-24 00:13:38');
INSERT INTO `AUTH_MENU` VALUES ('112', '', '延期', '25', 'sys:director:biz:delay:view', '2', '', '0', '2019-09-24 00:14:03');
INSERT INTO `AUTH_MENU` VALUES ('113', '', '客户服务', '0', '', '0', 'el-icon-s-custom', '18', '2019-09-26 17:13:24');
INSERT INTO `AUTH_MENU` VALUES ('114', '/customerMananger/customerManagerPending', '客户服务', '113', '', '1', 'el-icon-s-custom', '0', '2019-09-26 17:35:19');
INSERT INTO `AUTH_MENU` VALUES ('115', '', '查询', '114', 'sys:customermanager:pending:view', '2', '', '0', '2019-09-26 00:49:58');
INSERT INTO `AUTH_MENU` VALUES ('116', '', '流程日志', '114', 'sys:customermanager:pending:flow', '2', '', '0', '2019-09-26 00:50:23');
INSERT INTO `AUTH_MENU` VALUES ('117', '', '添加备注', '114', 'sys:customermanager:pending:addlog', '2', '', '0', '2019-09-26 00:50:50');
INSERT INTO `AUTH_MENU` VALUES ('118', '', '退款审批', '114', 'sys:customermanager:pending:drawback', '2', '', '0', '2019-09-26 00:51:13');
INSERT INTO `AUTH_MENU` VALUES ('119', '', '下一步', '114', 'sys:customermanager:pending:next', '2', '', '0', '2019-09-26 00:51:26');
INSERT INTO `AUTH_MENU` VALUES ('120', '', '删除', '16', 'sys:presales:business:del', '2', '', '0', '2019-10-13 17:29:52');
INSERT INTO `AUTH_MENU` VALUES ('121', '', '删除', '16', 'sys:presales:order:del', '2', '', '0', '2019-10-22 00:15:23');

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
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('2', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('2', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('2', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('2', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('3', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('4', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('5', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('6', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('6', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('6', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('6', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('6', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('7', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('7', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('7', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('7', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('7', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('8', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('8', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('9', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('9', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('9', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('9', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('9', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('10', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('10', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('10', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('10', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('10', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('11', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('11', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('11', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('11', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('11', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('12', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('12', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('12', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('12', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('12', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('13', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('13', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('13', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('13', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('13', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('14', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('14', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('14', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('14', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('14', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('15', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('15', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('15', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('15', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('16', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('16', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('16', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('16', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('17', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('17', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('17', 'R_WQ', '2019-09-25 11:36:51');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('17', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('18', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('18', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('18', 'R_WQ', '2019-09-25 11:36:51');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('18', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('19', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('19', 'R_SH', '2019-09-25 11:36:05');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('19', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('20', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('20', 'R_SH', '2019-09-25 11:36:05');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('20', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('21', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('21', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('21', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('22', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('22', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('22', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('23', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('24', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('24', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('24', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('24', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('24', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('25', 'ADMIN', '2019-09-18 23:35:18');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('25', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('25', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('25', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('25', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('26', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('27', 'R_TEST', '2019-09-19 00:38:23');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('42', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('42', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('42', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('43', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('43', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('43', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('44', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('44', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('45', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('45', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('46', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('46', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('46', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('48', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('48', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('48', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('49', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('49', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('49', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('50', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('50', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('50', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('51', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('51', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('52', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('52', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('52', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('53', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('53', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('54', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('54', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('54', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('55', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('55', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('55', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('56', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('56', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('57', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('57', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('57', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('58', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('58', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('58', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('59', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('59', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('59', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('60', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('60', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('60', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('61', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('61', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('61', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('62', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('62', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('63', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('63', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('63', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('64', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('64', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('64', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('65', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('65', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('66', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('66', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('67', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('67', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('67', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('68', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('68', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('69', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('69', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('70', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('70', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('71', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('71', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('72', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('72', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('73', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('73', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('74', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('74', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('75', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('75', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('77', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('77', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('78', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('78', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('79', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('79', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('80', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('80', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('81', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('81', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('82', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('82', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('83', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('83', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('84', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('84', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('85', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('85', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('86', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('86', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('87', 'R_WQ', '2019-09-25 11:36:51');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('87', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('88', 'R_WQ', '2019-09-25 11:36:51');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('88', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('89', 'R_WQ', '2019-09-25 11:36:51');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('89', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('91', 'R_WQ', '2019-09-25 11:36:51');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('91', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('92', 'R_SH', '2019-09-25 11:36:05');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('93', 'R_SH', '2019-09-25 11:36:05');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('94', 'R_SH', '2019-09-25 11:36:05');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('96', 'R_SH', '2019-09-25 11:36:05');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('98', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('99', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('100', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('102', 'R_CN', '2019-09-25 11:40:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('103', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('103', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('103', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('104', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('104', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('104', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('105', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('105', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('105', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('106', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('107', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('107', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('107', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('108', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('108', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('108', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('109', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('109', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('109', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('110', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('110', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('110', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('111', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('111', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('111', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('112', 'R_CWZG', '2019-09-25 11:39:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('112', 'R_SCZG', '2019-09-25 11:35:47');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('112', 'R_WQZG', '2019-10-17 15:55:35');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('120', 'R_KHJL', '2019-10-21 09:58:24');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('120', 'R_SQ', '2019-10-22 00:29:19');
INSERT INTO `AUTH_MENU_ROLE_REL` VALUES ('121', 'R_SQ', '2019-10-22 00:29:19');
