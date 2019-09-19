/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : work_flow

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-09-20 07:31:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for workflow_def_edge
-- ----------------------------
DROP TABLE IF EXISTS `workflow_def_edge`;
CREATE TABLE `workflow_def_edge` (
  `graph_id` varchar(45) NOT NULL,
  `edge_id` varchar(45) NOT NULL,
  `source_node` varchar(45) DEFAULT NULL,
  `target_node` varchar(45) DEFAULT NULL,
  `tag` varchar(45) DEFAULT NULL COMMENT '下一步（许可证办理...）',
  PRIMARY KEY (`edge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='workflow_def_edge';

-- ----------------------------
-- Records of workflow_def_edge
-- ----------------------------
INSERT INTO `workflow_def_edge` VALUES ('g_dljz', 'g_dljz_edge_cn_end', 'g_dljz_node_cn', 'g_dljz_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_dljz', 'g_dljz_edge_cwzg_khjl', 'g_dljz_node_cwzg', 'g_dljz_node_khjl', '客户服务');
INSERT INTO `workflow_def_edge` VALUES ('g_dljz', 'g_dljz_edge_cwzg_sczg', 'g_dljz_node_cwzg', 'g_dljz_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_dljz', 'g_dljz_edge_khjl_end', 'g_dljz_node_khjl', 'g_dljz_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_dljz', 'g_dljz_edge_sczg_cn', 'g_dljz_node_sczg', 'g_dljz_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_dljz', 'g_dljz_edge_sq_cwzg', 'g_dljz_node_sq', 'g_dljz_node_cwzg', '代理记账');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_cn_end', 'g_gjjdj_node_cn', 'g_gjjdj_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_sczg_cn', 'g_gjjdj_node_sczg', 'g_gjjdj_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_sh_end', 'g_gjjdj_node_sh', 'g_gjjdj_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_sh_sczg', 'g_gjjdj_node_sh', 'g_gjjdj_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_sh_sh', 'g_gjjdj_node_sh', 'g_gjjdj_node_sh', '公积金代缴');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_sh_wq', 'g_gjjdj_node_sh', 'g_gjjdj_node_wq', '外勤协办');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_sq_sh', 'g_gjjdj_node_sq', 'g_gjjdj_node_sh', '客户公司人数确认');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_edge_wq_sh', 'g_gjjdj_node_wq', 'g_gjjdj_node_sh', '公积金代缴');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_cn_end', 'g_gjjdk_node_cn', 'g_gjjdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sczg_cn', 'g_gjjdk_node_sczg', 'g_gjjdk_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sh_end', 'g_gjjdk_node_sh', 'g_gjjdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sh_sczg', 'g_gjjdk_node_sh', 'g_gjjdk_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sh_wq', 'g_gjjdk_node_sh', 'g_gjjdk_node_wq', '外勤协办');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sq_sh', 'g_gjjdk_node_sq', 'g_gjjdk_node_sh', '公积金代开');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_wq_sh', 'g_gjjdk_node_wq', 'g_gjjdk_node_sh', '公积金代开');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_cn_end', 'g_gsbg_node_cn', 'g_gsbg_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_sczg_cn', 'g_gsbg_node_sczg', 'g_gsbg_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_sh_end', 'g_gsbg_node_sh', 'g_gsbg_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_sh_sczg', 'g_gsbg_edge_sq_wq', 'g_gsbg_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_sq_wq', 'g_gsbg_node_sq', 'g_gsbg_node_wq', '工商变更');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_ddxz_shddxz_wqzgdyhkh', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_wqzgdyhkh', '待银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_ddxz_wqzgdhs_sczg', 'g_gszc_node_ddxz_wqzgdhs', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_shddxz_sczg', 'g_gszc_node_shddxz', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_shkhqz_sczg', 'g_gszc_node_shkhqz', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_shkhqz_shddxz', 'g_gszc_node_shkhqz', 'g_gszc_node_shddxz', '等待下证');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_shzlsh_sczg', 'g_gszc_node_shzlsh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_shzlsh_shkhqz', 'g_gszc_node_shzlsh', 'g_gszc_node_shkhqz', '客户签字');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_shzlsj_sczg', 'g_gszc_node_shzlsj', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_shzlsj_shzlsh', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '资料审核');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_edge_sq_shzlsj', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '资料收集');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_shddxz_shyhkhzzkh', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_shyhkhzzkh', '银行客户自助开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh_sczg', 'g_gszc_node_ddxz_shyhkhzzkh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh_wqzgdhs', 'g_gszc_node_ddxz_shyhkhzzkh', 'g_gszc_node_shyhkhzzkh_wqzgdhs', '待核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqyhkh_shdhzfhs', 'g_gszc_node_ddxz_wqzgdyhkh', 'g_gszc_node_shdhzfhs', '待合作方核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqzgdhs_wqhs', 'g_gszc_node_ddxz_wqzgdhs', 'g_gszc_node_ddxz_wqhs', '核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqzgdyhkh_wqyhkh', 'g_gszc_node_ddxz_wqzgdyhkh', 'g_gszc_node_ddxz_wqyhkh', '银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_shyhkhzzkh_end', 'g_gszc_node_hs_shyhkhzzkh', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_shyhkhzzkh_sczg', 'g_gszc_node_hs_shyhkhzzkh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqhs_shyhkhzzkh', 'g_gszc_node_ddxz_wqhs', 'g_gszc_node_hs_shyhkhzzkh', '银行客户自助开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqhs_wqzgdyhkh', 'g_gszc_node_ddxz_wqhs', 'g_gszc_node_hs_wqzgdyhkh', '待银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqyhkh_end', 'g_gszc_node_hs_wqyhkh', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqyhkh_sczg', 'g_gszc_node_hs_wqyhkh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqzgdyhkh_wqyhkh', 'g_gszc_node_hs_wqzgdyhkh', 'g_gszc_node_hs_wqyhkh', '银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_sczg_end', 'g_gszc_node_sczg', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shddxz_ddxz_wqzgdhs', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_wqzgdhs', '待核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shdhzfhs_end', 'g_gszc_node_shdhzfhs', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqhs_end', 'g_gszc_node_shyhkhzzkh_wqhs', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqhs_sczg', 'g_gszc_node_shyhkhzzkh_wqhs', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqzgdhs_wqhs', 'g_gszc_node_shyhkhzzkh_wqzgdhs', 'g_gszc_node_shyhkhzzkh_wqhs', '核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_wqzgdyhkh_sczg', 'g_gszc_node_shdhzfhs', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_edge_cn_end', 'g_gszx_node_cn', 'g_gszx_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_edge_sczg_cn', 'g_gszx_node_sczg', 'g_gszx_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_edge_sh_end', 'g_gszx_node_sh', 'g_gszx_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_edge_sh_sczg', 'g_gszx_edge_sq_wq', 'g_gszx_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_edge_sq_wq', 'g_gszx_node_sq', 'g_gszx_node_wq', '公司注销');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_cn_end', 'g_sbdk_node_cn', 'g_sbdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sczg_cn', 'g_sbdk_node_sczg', 'g_sbdk_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sh_end', 'g_sbdk_node_sh', 'g_sbdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sh_sczg', 'g_sbdk_node_sh', 'g_sbdk_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sh_wq', 'g_sbdk_node_sh', 'g_sbdk_node_wq', '外勤协办');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sq_sh', 'g_sbdk_node_sq', 'g_sbdk_node_sh', '社保代开');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_wq_sh', 'g_sbdk_node_wq', 'g_sbdk_node_sh', '社保代开');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_cn_end', 'g_sbzc_node_cn', 'g_sbzc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sczg_cn', 'g_sbzc_node_sczg', 'g_sbzc_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sh_end', 'g_sbzc_node_sh', 'g_sbzc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sh_sczg', 'g_sbzc_node_sh', 'g_sbzc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sq_sh', 'g_sbzc_node_sq', 'g_sbzc_node_sh', '商标注册');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_cn_end', 'g_xkzbl_node_cn', 'g_xkzbl_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sczg_cn', 'g_xkzbl_node_sczg', 'g_xkzbl_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sh_end', 'g_xkzbl_node_sh', 'g_xkzbl_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sh_sczg', 'g_xkzbl_node_sh', 'g_xkzbl_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sq_sh', 'g_xkzbl_node_sq', 'g_xkzbl_node_sh', '许可证办理');

-- ----------------------------
-- Table structure for workflow_def_graph
-- ----------------------------
DROP TABLE IF EXISTS `workflow_def_graph`;
CREATE TABLE `workflow_def_graph` (
  `graph_id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `root_node` varchar(45) NOT NULL,
  `end_node` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`graph_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流定义';

-- ----------------------------
-- Records of workflow_def_graph
-- ----------------------------
INSERT INTO `workflow_def_graph` VALUES ('g_dljz', '代理记账', 'g_dljz_node_sq', 'g_dljz_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_gjjdj', '公积金代缴', 'g_gjjdj_node_sq', 'g_gjjdk_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_gjjdk', '公积金代开', 'g_gjjdk_node_sq', 'g_gjjdk_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_gsbg', '工商变更', 'g_gsbg_node_sq', 'g_gsbg_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_gszc', '公司注册', 'g_gszc_node_sq', 'g_gszc_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_gszx', '工商注销', 'g_gszx_node_sq', 'g_gszx_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_sbdj', '社保代缴', 'g_sbdj_node_sq', 'g_sbdj_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_sbdk', '社保代开', 'g_sbdk_node_sq', 'g_sbdk_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_sbzc', '商标注册', 'g_sbzc_node_sq', 'g_sbzc_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_xkzbl', '许可证办理', 'g_xkzbl_node_sq', 'g_xkzbl_node_end');

-- ----------------------------
-- Table structure for workflow_def_graph_business_rel
-- ----------------------------
DROP TABLE IF EXISTS `workflow_def_graph_business_rel`;
CREATE TABLE `workflow_def_graph_business_rel` (
  `graph_id` varchar(45) NOT NULL,
  `business_id` varchar(45) NOT NULL,
  PRIMARY KEY (`graph_id`,`business_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflow_def_graph_business_rel
-- ----------------------------
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_dljz', 'BIZ_3');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gjjdj', '');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gjjdj', 'BIZ_1');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gjjdk', 'BIZ_5');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gsbg', 'BIZ_6');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gszc', 'BIZ_4');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gszx', 'BIZ_7');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_sbdj', 'BIZ_2');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_sbdk', 'BIZ_8');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_sbzc', 'BIZ_9');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_xkzbl', 'BIZ_10');

-- ----------------------------
-- Table structure for workflow_def_node
-- ----------------------------
DROP TABLE IF EXISTS `workflow_def_node`;
CREATE TABLE `workflow_def_node` (
  `graph_id` varchar(45) NOT NULL COMMENT '唯一主键不可复用',
  `node_id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流节点定义';

-- ----------------------------
-- Records of workflow_def_node
-- ----------------------------
INSERT INTO `workflow_def_node` VALUES ('g_dljz', 'g_dljz_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_dljz', 'g_dljz_node_cwzg', '代理记账', 'R_CWZG');
INSERT INTO `workflow_def_node` VALUES ('g_dljz', 'g_dljz_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_dljz', 'g_dljz_node_khjl', '客户服务', 'R_KHJL');
INSERT INTO `workflow_def_node` VALUES ('g_dljz', 'g_dljz_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_dljz', 'g_dljz_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_dljz', 'g_dljz_node_sys', '系统触发', '');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdj', 'g_gjjdj_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdj', 'g_gjjdj_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdj', 'g_gjjdj_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdj', 'g_gjjdj_node_sh', '公积金代缴', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdj', 'g_gjjdj_node_shrsrq', '客户公司人数确认', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdj', 'g_gjjdj_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdj', 'g_gjjdj_node_wq', '外勤协办', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdk', 'g_gjjdk_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdk', 'g_gjjdk_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdk', 'g_gjjdk_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdk', 'g_gjjdk_node_sh', '公积金代开', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdk', 'g_gjjdk_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_gjjdk', 'g_gjjdk_node_wq', '外勤协办', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gsbg', 'g_gsbg_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_gsbg', 'g_gsbg_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_gsbg', 'g_gsbg_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_gsbg', 'g_gsbg_node_sh', '工商变更', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gsbg', 'g_gsbg_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh', '银行客户自主开户', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqhs', '核税', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqyhkh', '银行开户', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqzgdhs', '待核税', 'R_WQZG');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqzgdyhkh', '待银行开户', 'R_WQZG');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_hs_shyhkhzzkh', '银行客户自主开户', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_hs_wqyhkh', '银行开户', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_hs_wqzgdyhkh', '待银行开户', 'R_WQZG');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_shddxz', '等待下证', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_shdhzfhs', '待合作方核税', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_shkhqz', '客户签字', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqhs', '核税', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqzgdhs', '待核税', 'R_WQZG');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_shzlsh', '资料审核', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_shzlsj', '资料收集', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_sq', '公司注册', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_sh', '公司注销', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_sbdj', 'g_sbdj_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_sbdj', 'g_sbdj_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_sbdj', 'g_sbdj_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_sbdj', 'g_sbdj_node_sh', '社保代缴', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_sbdj', 'g_sbdj_node_shrsrq', '客户公司人数确认', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_sbdj', 'g_sbdj_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_sbdj', 'g_sbdj_node_wq', '外勤协办', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_sbdk', 'g_sbdk_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_sbdk', 'g_sbdk_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_sbdk', 'g_sbdk_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_sbdk', 'g_sbdk_node_sh', '社保代开', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_sbdk', 'g_sbdk_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_sbdk', 'g_sbdk_node_wq', '外勤协办', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_sh', '商标注册', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_xkzbl', 'g_xkzbl_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_xkzbl', 'g_xkzbl_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_xkzbl', 'g_xkzbl_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_xkzbl', 'g_xkzbl_node_sh', '许可证办理', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_xkzbl', 'g_xkzbl_node_sq', '客户登记', 'R_SQ');

-- ----------------------------
-- Table structure for workflow_def_node_ext_attr
-- ----------------------------
DROP TABLE IF EXISTS `workflow_def_node_ext_attr`;
CREATE TABLE `workflow_def_node_ext_attr` (
  `node_id` varchar(45) NOT NULL,
  `rest_days` varchar(45) DEFAULT NULL COMMENT '告警剩余时间',
  `next_node_trigger_handler` varchar(45) DEFAULT NULL COMMENT '回调处理',
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflow_def_node_ext_attr
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_event
-- ----------------------------
DROP TABLE IF EXISTS `workflow_event`;
CREATE TABLE `workflow_event` (
  `event_id` varchar(45) NOT NULL,
  `graph_id` varchar(45) DEFAULT NULL,
  `cur_node_id` varchar(45) DEFAULT NULL,
  `event_owner` varchar(45) DEFAULT NULL,
  `cur_operator` varchar(45) DEFAULT NULL,
  `cur_operator_name` varchar(255) DEFAULT NULL,
  `begin_date` datetime DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件';

-- ----------------------------
-- Records of workflow_event
-- ----------------------------
INSERT INTO `workflow_event` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_khjl', '8', '8', '我是财务主管2', '2019-09-17 00:39:16');

-- ----------------------------
-- Table structure for workflow_event_dependency
-- ----------------------------
DROP TABLE IF EXISTS `workflow_event_dependency`;
CREATE TABLE `workflow_event_dependency` (
  `pre_event` varchar(45) NOT NULL,
  `next_event` varchar(45) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`pre_event`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件依赖';

-- ----------------------------
-- Records of workflow_event_dependency
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_event_dependency_his
-- ----------------------------
DROP TABLE IF EXISTS `workflow_event_dependency_his`;
CREATE TABLE `workflow_event_dependency_his` (
  `pre_event` varchar(45) NOT NULL,
  `next_event` varchar(45) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `finish_time` date DEFAULT NULL,
  PRIMARY KEY (`pre_event`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件依赖历史表';

-- ----------------------------
-- Records of workflow_event_dependency_his
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_event_history
-- ----------------------------
DROP TABLE IF EXISTS `workflow_event_history`;
CREATE TABLE `workflow_event_history` (
  `event_id` varchar(45) NOT NULL,
  `graph_id` varchar(45) NOT NULL,
  `node_id` varchar(45) NOT NULL,
  `begin_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `operator` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件历史';

-- ----------------------------
-- Records of workflow_event_history
-- ----------------------------
INSERT INTO `workflow_event_history` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_sq', '2019-09-03', '2019-09-03', '1');
INSERT INTO `workflow_event_history` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_cwzg', '2019-09-03', '2019-09-03', '8');
INSERT INTO `workflow_event_history` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_khjl', '2019-09-14', null, '5');
INSERT INTO `workflow_event_history` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_khjl', '2019-09-17', null, '8');
INSERT INTO `workflow_event_history` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_khjl', '2019-09-17', null, '8');

-- ----------------------------
-- Table structure for workflow_log_attachment
-- ----------------------------
DROP TABLE IF EXISTS `workflow_log_attachment`;
CREATE TABLE `workflow_log_attachment` (
  `file_id` varchar(45) NOT NULL,
  `log_id` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `file_path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程日志附件表';

-- ----------------------------
-- Records of workflow_log_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_log_message
-- ----------------------------
DROP TABLE IF EXISTS `workflow_log_message`;
CREATE TABLE `workflow_log_message` (
  `log_id` varchar(45) NOT NULL,
  `operator_name` varchar(45) DEFAULT NULL,
  `operator` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  `event_id` varchar(45) DEFAULT NULL,
  `has_attachment` varchar(1) DEFAULT '0',
  `attachments_path` varchar(300) DEFAULT NULL,
  `has_refund` varchar(1) DEFAULT NULL,
  `refund_fee` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程日志表';

-- ----------------------------
-- Records of workflow_log_message
-- ----------------------------
INSERT INTO `workflow_log_message` VALUES ('1233', '我是主管1', '8', '2019-09-08 15:29:10', '马云爸爸答应给我们投资个小目标', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, '', null);
INSERT INTO `workflow_log_message` VALUES ('888a835cdbcb419eac9da146626be731', '我是主管1', '8', '2019-09-17 00:35:13', '不同意退款，请老总批示', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('a55347d85230447a8a0892cc752f8227', '我是主管1', '8', '2019-09-15 19:32:28', '这个案子处理过程很复杂,需要延时10天', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('d62098fd55524aafaa3cd42f1c10d2f8', '我是主管1', '8', '2019-09-17 00:39:16', '不同意退款，请老总批示', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('ff605d17289a4014b2295f4d967fad57', '我是主管1', '8', '2019-09-14 11:30:48', '请快速解决！', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null);

-- ----------------------------
-- Procedure structure for pr_convert_dbtab_utf8
-- ----------------------------
DROP PROCEDURE IF EXISTS `pr_convert_dbtab_utf8`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `pr_convert_dbtab_utf8`(IN dbName varchar(100))
BEGIN
 
    declare stop int default 0;
 
    declare tabCount int default 0;
 
    declare strSql varchar(1000);
 
    declare name varchar(100);
 
    declare cur CURSOR FOR select table_name from information_schema.tables where table_schema=dbName;
 
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
