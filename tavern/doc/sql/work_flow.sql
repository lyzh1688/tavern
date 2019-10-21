/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : work_flow

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-10-22 00:40:49
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
INSERT INTO `workflow_def_edge` VALUES ('g_dljz', 'g_dljz_edge_node_end_end', 'g_dljz_node_end', '', '结束');
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
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdj', 'g_gjjdj_node_end_end', 'g_gjjdj_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_cn_end', 'g_gjjdk_node_cn', 'g_gjjdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_node_end_end', 'g_gjjdk_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sczg_cn', 'g_gjjdk_node_sczg', 'g_gjjdk_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sh_end', 'g_gjjdk_node_sh', 'g_gjjdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sh_sczg', 'g_gjjdk_node_sh', 'g_gjjdk_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sh_wq', 'g_gjjdk_node_sh', 'g_gjjdk_node_wq', '外勤协办');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_sq_sh', 'g_gjjdk_node_sq', 'g_gjjdk_node_sh', '公积金代开');
INSERT INTO `workflow_def_edge` VALUES ('g_gjjdk', 'g_gjjdk_edge_wq_sh', 'g_gjjdk_node_wq', 'g_gjjdk_node_sh', '公积金代开');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_cn_end', 'g_gsbg_node_cn', 'g_gsbg_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_node_end_end', 'g_gsbg_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_sczg_cn', 'g_gsbg_node_sczg', 'g_gsbg_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_sh_end', 'g_gsbg_node_wq', 'g_gsbg_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gsbg', 'g_gsbg_edge_sh_sczg', 'g_gsbg_node_wq', 'g_gsbg_node_sczg', '退款审批');
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
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_cn_end', 'g_gszc_node_cn', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_shddxz_shyhkhzzkh', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_shyhkhzzkh', '银行客户自助开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_shdhzfhs_sczg', 'g_gszc_node_shdhzfhs', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh_sczg', 'g_gszc_node_ddxz_shyhkhzzkh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh_wqzgdhs', 'g_gszc_node_ddxz_shyhkhzzkh', 'g_gszc_node_shyhkhzzkh_wqzgdhs', '待核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqyhkh_sczg', 'g_gszc_node_ddxz_wqyhkh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqyhkh_shdhzfhs', 'g_gszc_node_ddxz_wqyhkh', 'g_gszc_node_shdhzfhs', '待合作方核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqzgdhs_shhs', 'g_gszc_node_ddxz_wqzgdhs', 'g_gszc_node_ddxz_shhs', '售后核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqzgdhs_wqhs', 'g_gszc_node_ddxz_wqzgdhs', 'g_gszc_node_ddxz_wqhs', '外勤核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqzgdyhkh_wqyhkh', 'g_gszc_node_ddxz_wqzgdyhkh', 'g_gszc_node_ddxz_wqyhkh', '银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_end_end', 'g_gszc_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_shhs_shyhkhzzkh', 'g_gszc_node_ddxz_shhs', 'g_gszc_node_hs_shyhkhzzkh', '银行客户自助开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_shhs_wqzgdyhkh', 'g_gszc_node_ddxz_shhs', 'g_gszc_node_hs_wqzgdyhkh', '待银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_shyhkhzzkh_sczg', 'g_gszc_node_hs_shyhkhzzkh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqhs_shyhkhzzkh', 'g_gszc_node_ddxz_wqhs', 'g_gszc_node_hs_shyhkhzzkh', '银行客户自助开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqhs_wqzgdyhkh', 'g_gszc_node_ddxz_wqhs', 'g_gszc_node_hs_wqzgdyhkh', '待银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqyhkh_end', 'g_gszc_node_hs_wqyhkh', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqyhkh_sczg', 'g_gszc_node_hs_wqyhkh', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_hs_wqzgdyhkh_wqyhkh', 'g_gszc_node_hs_wqzgdyhkh', 'g_gszc_node_hs_wqyhkh', '银行开户');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_sczg_cn', 'g_gszc_node_sczg', 'g_gszc_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shddxz_ddxz_wqzgdhs', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_wqzgdhs', '待核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shdhzfhs_end', 'g_gszc_node_shdhzfhs', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqhs_end', 'g_gszc_node_shyhkhzzkh_wqhs', 'g_gszc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqhs_sczg', 'g_gszc_node_shyhkhzzkh_wqhs', 'g_gszc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszc', 'g_gszc_node_shyhkhzzkh_wqzgdhs_wqhs', 'g_gszc_node_shyhkhzzkh_wqzgdhs', 'g_gszc_node_shyhkhzzkh_wqhs', '核税');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_db_gszx', 'g_gszx_node_db', 'g_gszx_node_gszx', '登报');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_db_tksp', 'g_gszx_node_db', 'g_gszx_node_tksp', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_end_end', 'g_gszx_node_end', null, '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_gjjsbzx_tksp', 'g_gszx_node_gjjsbzx', 'g_gszx_node_tksp', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_gjjsbzx_yhzx', 'g_gszx_node_gjjsbzx', 'g_gszx_node_yhzx', '公积金社保注销');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_gszxkhdj_swzx', 'g_gszx_node_gszxkhdj', 'g_gszx_node_swzx', '公司注销客户登记');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_gszx_gjjsbzx', 'g_gszx_node_gszx', 'g_gszx_node_gjjsbzx', '工商注销');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_gszx_tksp', 'g_gszx_node_gszx', 'g_gszx_node_tksp', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_swzx_db', 'g_gszx_node_swzx', 'g_gszx_node_db', '税务注销');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_swzx_tksp', 'g_gszx_node_swzx', 'g_gszx_node_tksp', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_tksp_tk', 'g_gszx_node_tksp', 'g_gszx_node_tk', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_tk_end', 'g_gszx_node_tk', 'g_gszx_node_end', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_yhzx_end', 'g_gszx_node_yhzx', 'g_gszx_node_end', '银行注销');
INSERT INTO `workflow_def_edge` VALUES ('g_gszx', 'g_gszx_node_yhzx_tksp', 'g_gszx_node_yhzx', 'g_gszx_node_tksp', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_cn_end', 'g_sbdj_node_cn', 'g_sbdj_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_node_end_end', 'g_sbdj_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_sczg_cn', 'g_sbdj_node_sczg', 'g_sbdj_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_sh_end', 'g_sbdj_node_sh', 'g_sbdj_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_sh_sczg', 'g_sbdj_node_sh', 'g_sbdj_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_sh_sh', 'g_sbdj_node_sh', 'g_sbdj_node_sh', '社保代缴');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_sh_wq', 'g_sbdj_node_sh', 'g_sbdj_node_wq', '外勤协办');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_sq_sh', 'g_sbdj_node_sq', 'g_sbdj_node_sh', '客户公司人数确认');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdj', 'g_sbdj_edge_wq_sh', 'g_sbdj_node_wq', 'g_sbdj_node_sh', '社保代缴');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_cn_end', 'g_sbdk_node_cn', 'g_sbdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_node_end_end', 'g_sbdk_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sczg_cn', 'g_sbdk_node_sczg', 'g_sbdk_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sh_end', 'g_sbdk_node_sh', 'g_sbdk_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sh_sczg', 'g_sbdk_node_sh', 'g_sbdk_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sh_wq', 'g_sbdk_node_sh', 'g_sbdk_node_wq', '外勤协办');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_sq_sh', 'g_sbdk_node_sq', 'g_sbdk_node_sh', '社保代开');
INSERT INTO `workflow_def_edge` VALUES ('g_sbdk', 'g_sbdk_edge_wq_sh', 'g_sbdk_node_wq', 'g_sbdk_node_sh', '社保代开');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_cn_end', 'g_sbzc_node_cn', 'g_sbzc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_node_end_end', 'g_sbzc_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sczg_cn', 'g_sbzc_node_sczg', 'g_sbzc_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sh_end', 'g_sbzc_node_sh', 'g_sbzc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sh_sczg', 'g_sbzc_node_sh', 'g_sbzc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sq_sh', 'g_sbzc_node_sq', 'g_sbzc_node_sh', '商标注册');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_cn_end', 'g_xkzbl_node_cn', 'g_xkzbl_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_node_end_end', 'g_xkzbl_node_end', '', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sczg_cn', 'g_xkzbl_node_sczg', 'g_xkzbl_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sh_end', 'g_xkzbl_node_sh', 'g_xkzbl_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sh_sczg', 'g_xkzbl_node_sh', 'g_xkzbl_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_xkzbl', 'g_xkzbl_edge_sq_sh', 'g_xkzbl_node_sq', 'g_xkzbl_node_sh', '许可证办理');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_end_end', 'g_ycyw_end', null, '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_sq_xcyccl', 'g_ycyw_node_sq', 'g_ycyw_xcyccl', '异常业务');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_sq_ycyccl', 'g_ycyw_node_sq', 'g_ycyw_ycyccl', '异常业务');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_tksp_tk', 'g_ycyw_tksp', 'g_ycyw_tk', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_tk_end', 'g_ycyw_tk', 'g_ycyw_end', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_xcjcyc_end', 'g_ycyw_xcjcyc', 'g_ycyw_end', '现场解除异常');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_xcjcyc_tksp', 'g_ycyw_xcjcyc', 'g_ycyw_tksp', '现场解除异常');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_xcyccl_tksp', 'g_ycyw_xcyccl', 'g_ycyw_tksp', '现场异常处理');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_xcyccl_xcjcyc', 'g_ycyw_xcyccl', 'g_ycyw_xcjcyc', '现场异常处理');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_xcyccl_ycjcyc', 'g_ycyw_xcyccl', 'g_ycyw_ycjcyc', '现场异常处理');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_ycjcyc_end', 'g_ycyw_ycjcyc', 'g_ycyw_end', '远程解除异常');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_ycjcyc_tksp', 'g_ycyw_ycjcyc', 'g_ycyw_tksp', '远程解除异常常');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_ycyccl_tksp', 'g_ycyw_ycyccl', 'g_ycyw_tksp', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_ycyccl_xcjcyc', 'g_ycyw_ycyccl', 'g_ycyw_xcjcyc', '远程异常处理');
INSERT INTO `workflow_def_edge` VALUES ('g_ycyw', 'g_ycyw_node_ycyccl_ycjcyc', 'g_ycyw_ycyccl', 'g_ycyw_ycjcyc', '远程异常处理');

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
INSERT INTO `workflow_def_graph` VALUES ('g_gszx', '公司注销', 'g_gszx_node_sq', 'g_gszx_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_sbdj', '社保代缴', 'g_sbdj_node_sq', 'g_sbdj_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_sbdk', '社保代开', 'g_sbdk_node_sq', 'g_sbdk_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_sbzc', '商标注册', 'g_sbzc_node_sq', 'g_sbzc_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_xkzbl', '许可证办理', 'g_xkzbl_node_sq', 'g_xkzbl_node_end');
INSERT INTO `workflow_def_graph` VALUES ('g_ycyw', '异常业务', 'g_ycyw_node_sq', 'g_ycyw_node_end');

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
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gjjdj', 'BIZ_1');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gjjdk', 'BIZ_5');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gsbg', 'BIZ_6');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gszc', 'BIZ_4');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_gszx', 'BIZ_7');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_sbdj', 'BIZ_2');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_sbdk', 'BIZ_8');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_sbzc', 'BIZ_9');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_xkzbl', 'BIZ_10');
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_ycyw', 'BIZ_11');

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
INSERT INTO `workflow_def_node` VALUES ('g_gsbg', 'g_gsbg_node_sq', '客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_gsbg', 'g_gsbg_node_wq', '工商变更', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_shddxz_shyhkhzzkh', '银行客户自助开户', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_shhs', '售后核税', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh', '银行客户自主开户', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_gszc', 'g_gszc_node_ddxz_wqhs', '外勤核税', 'R_WQ');
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
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_db', '登报', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_gjjsbzx', '公积金社保注销', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_gszx', '工商注销', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_gszxkhdj', '公司注销客户登记', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_swzx', '税务注销', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_tk', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_tksp', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_gszx', 'g_gszx_node_yhzx', '银行注销', 'R_WQ');
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
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_end', '结束', null);
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_node_sq', '异常业务', 'R_SQ');
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_tk', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_tksp', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_xcjcyc', '现场解除异常', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_xcyccl', '现场异常处理', 'R_WQ');
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_ycjcyc', '远程解除异常', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_ycyw', 'g_ycyw_ycyccl', '远程异常处理', 'R_SH');

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
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_dljz_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_dljz_node_cwzg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_dljz_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_dljz_node_khjl', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_dljz_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_dljz_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_dljz_node_sys', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdj_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdj_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdj_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdj_node_sh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdj_node_shrsrq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdj_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdj_node_wq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdk_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdk_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdk_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdk_node_sh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdk_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gjjdk_node_wq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gsbg_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gsbg_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gsbg_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gsbg_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gsbg_node_wq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_ddxz_shddxz_shyhkhzzkh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_ddxz_shhs', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_ddxz_shyhkhzzkh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_ddxz_wqhs', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_ddxz_wqyhkh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_ddxz_wqzgdhs', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_ddxz_wqzgdyhkh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_hs_shyhkhzzkh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_hs_wqyhkh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_hs_wqzgdyhkh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_shddxz', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_shdhzfhs', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_shkhqz', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_shyhkhzzkh_wqhs', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_shyhkhzzkh_wqzgdhs', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_shzlsh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_shzlsj', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszc_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_db', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_gjjsbzx', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_gszx', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_gszxkhdj', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_swzx', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_tk', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_tksp', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_gszx_node_yhzx', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdj_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdj_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdj_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdj_node_sh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdj_node_shrsrq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdj_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdj_node_wq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdk_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdk_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdk_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdk_node_sh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdk_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbdk_node_wq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbzc_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbzc_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbzc_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbzc_node_sh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_sbzc_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_xkzbl_node_cn', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_xkzbl_node_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_xkzbl_node_sczg', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_xkzbl_node_sh', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_xkzbl_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_end', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_node_sq', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_tk', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_tksp', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_xcjcyc', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_xcyccl', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_ycjcyc', '2', null);
INSERT INTO `workflow_def_node_ext_attr` VALUES ('g_ycyw_ycyccl', '2', null);

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
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件';

-- ----------------------------
-- Records of workflow_event
-- ----------------------------
INSERT INTO `workflow_event` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_end', '3d2c42b03ead49dba7d65ddf1f854428', '', '', '2019-10-17 15:19:45', '2019-10-19 15:19:45');
INSERT INTO `workflow_event` VALUES ('48ec0b235eed4c49868570959b95507f', 'g_gsbg', 'g_gsbg_node_wq', 'e31758ba68804628aff180ceb271650b', 'e31758ba68804628aff180ceb271650b', '钮思傲', '2019-10-17 16:03:08', '2019-10-19 16:03:08');
INSERT INTO `workflow_event` VALUES ('6ccaec607d5443fba4628a34f3f66ca2', 'g_sbzc', 'g_sbzc_node_sh', '167390cb434c4d539c9ba1760ba1e901', '167390cb434c4d539c9ba1760ba1e901', '魏稳根', '2019-10-18 15:29:33', '2019-10-20 15:29:33');
INSERT INTO `workflow_event` VALUES ('80707a12ccd84ebeb98da4a4f8c00786', 'g_xkzbl', 'g_xkzbl_node_sh', '3d2c42b03ead49dba7d65ddf1f854428', '3d2c42b03ead49dba7d65ddf1f854428', '陈峰', '2019-10-21 11:27:33', '2019-10-23 11:27:33');
INSERT INTO `workflow_event` VALUES ('e80c2efa21c045fa9d275722372fc238', 'g_gszx', 'g_gszx_node_wq', '2869b57d6832449c916dbbb4e2128cac', '2869b57d6832449c916dbbb4e2128cac', '郑安心', '2019-10-17 15:48:45', '2019-10-19 15:48:45');
INSERT INTO `workflow_event` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_shdhzfhs', '3d2c42b03ead49dba7d65ddf1f854428', '167390cb434c4d539c9ba1760ba1e901', '魏稳根', '2019-10-17 22:49:29', '2019-10-19 22:49:29');

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
  `next_node_id` varchar(45) NOT NULL,
  `begin_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `operator` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件历史';

-- ----------------------------
-- Records of workflow_event_history
-- ----------------------------
INSERT INTO `workflow_event_history` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_sq', 'g_dljz_node_cwzg', '2019-09-03', '2019-09-03', '2');
INSERT INTO `workflow_event_history` VALUES ('63a69f219efc4ccb96e6b9013fb24d0e', 'g_dljz', 'g_dljz_node_cwzg', 'g_dljz_node_khjl', '2019-09-03', '2019-09-03', '8');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-09-25', '2019-09-25', '2');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-09-25', '2019-09-25', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_shkhqz', '2019-09-26', null, 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_shkhqz', 'g_gszc_node_shddxz', '2019-09-26', null, 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_wqzgdhs', '2019-09-26', null, 'c377a64ceb244ce0a3174e75393f1820');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_ddxz_wqhs', 'g_gszc_node_hs_wqzgdyhkh', '2019-09-26', null, '894617b1ab9d4289a0192f6556579d03');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_hs_wqzgdyhkh', 'g_gszc_node_hs_wqyhkh', '2019-09-26', null, 'cdacf700cf484ee68163c5fcdf21906d');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_hs_wqyhkh', 'g_gszc_node_end', '2019-09-26', null, '42ed651d283b4019aaab51117063b5f1');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_end', '', '2019-09-26', null, 'undefined');
INSERT INTO `workflow_event_history` VALUES ('6550e24c4bed45dc9c59e42c1ec681d9', 'g_gsbg', 'g_gsbg_node_sq', 'g_gsbg_node_wq', '2019-09-26', '2019-09-26', '2');
INSERT INTO `workflow_event_history` VALUES ('021a3c942b07499ea99c4b6cd8cd0523', 'g_gszx', 'g_gszx_node_sq', 'g_gszx_node_wq', '2019-09-26', '2019-09-26', '2');
INSERT INTO `workflow_event_history` VALUES ('a397a04b1ab042a589e322c23bc6ef94', 'g_xkzbl', 'g_xkzbl_node_sq', 'g_xkzbl_node_sh', '2019-09-26', '2019-09-26', '2');
INSERT INTO `workflow_event_history` VALUES ('e9a1be1141a34e9cb07fbbc2d9d2f23f', 'g_gjjdk', 'g_gjjdk_node_sq', 'g_gjjdk_node_sh', '2019-09-26', '2019-09-26', '2');
INSERT INTO `workflow_event_history` VALUES ('e9a1be1141a34e9cb07fbbc2d9d2f23f', 'g_gjjdk', 'g_gjjdk_node_sh', 'g_gjjdk_node_sczg', '2019-10-09', null, 'c377a64ceb244ce0a3174e75393f1820');
INSERT INTO `workflow_event_history` VALUES ('e9a1be1141a34e9cb07fbbc2d9d2f23f', 'g_gjjdk', 'g_gjjdk_node_sczg', 'g_gjjdk_node_cn', '2019-10-09', null, '6');
INSERT INTO `workflow_event_history` VALUES ('e9a1be1141a34e9cb07fbbc2d9d2f23f', 'g_gjjdk', 'g_gjjdk_node_cn', 'g_gjjdk_node_end', '2019-10-09', null, '3');
INSERT INTO `workflow_event_history` VALUES ('e9a1be1141a34e9cb07fbbc2d9d2f23f', 'g_gjjdk', 'g_gjjdk_node_end', '', '2019-10-09', null, '');
INSERT INTO `workflow_event_history` VALUES ('e2fb75f5b0674b139c34191384a987ef', 'g_gszc', 'g_gszc_node_ddxz_wqzgdhs', 'g_gszc_node_ddxz_wqhs', '2019-09-26', '0000-00-00', 'c377a64ceb244ce0a3174e75393f1820');
INSERT INTO `workflow_event_history` VALUES ('ea47292a2b3f436fa38650e6b496a367', 'g_gjjdk', 'g_gjjdk_node_sq', 'g_gjjdk_node_sh', '2019-10-11', '2019-10-11', '1');
INSERT INTO `workflow_event_history` VALUES ('b3286a15d4604d73b64a636307cb9e6c', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-11', '2019-10-11', '1');
INSERT INTO `workflow_event_history` VALUES ('74149bd10aef457d9f2ce1a7e3f28077', 'g_gszx', 'g_gszx_node_sq', 'g_gszx_node_wq', '2019-10-11', '2019-10-11', '1');
INSERT INTO `workflow_event_history` VALUES ('b3286a15d4604d73b64a636307cb9e6c', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-11', null, 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('b3286a15d4604d73b64a636307cb9e6c', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_sczg', '2019-10-11', null, '7');
INSERT INTO `workflow_event_history` VALUES ('74149bd10aef457d9f2ce1a7e3f28077', 'g_gszx', 'g_gszx_node_wq', 'g_gszx_node_end', '2019-10-11', null, '');
INSERT INTO `workflow_event_history` VALUES ('5b73e26c064f4e5db174cfd515952852', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-12', '2019-10-12', '2');
INSERT INTO `workflow_event_history` VALUES ('65792f61524048d393a96fc6a1311d37', 'g_gjjdj', 'g_gjjdj_node_sq', 'g_gjjdj_node_sh', '2019-10-12', '2019-10-12', 'c377a64ceb244ce0a3174e75393f1820');
INSERT INTO `workflow_event_history` VALUES ('5b73e26c064f4e5db174cfd515952852', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-12', null, 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('5b73e26c064f4e5db174cfd515952852', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_shkhqz', '2019-10-12', null, 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('b1533c294e424deaaba752d9621d3971', 'g_gjjdj', 'g_gjjdj_node_sq', 'g_gjjdj_node_sh', '2019-10-12', '2019-10-12', '1');
INSERT INTO `workflow_event_history` VALUES ('d74393ec477d40f788ef308f0189c246', 'g_xkzbl', 'g_xkzbl_node_sq', 'g_xkzbl_node_sh', '2019-10-12', '2019-10-12', '1');
INSERT INTO `workflow_event_history` VALUES ('7b1edf23d02d4f0185439ae87b4416b8', 'g_sbdj', 'g_sbdj_node_sq', 'g_sbdj_node_sh', '2019-10-12', '2019-10-12', '1');
INSERT INTO `workflow_event_history` VALUES ('ebcf732e7612463d8377f210bc4dac32', 'g_dljz', 'g_dljz_node_sq', 'g_dljz_node_cwzg', '2019-10-12', '2019-10-12', '1');
INSERT INTO `workflow_event_history` VALUES ('8681a7eff5fc42b68bd99b12df110843', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-12', '2019-10-12', '1');
INSERT INTO `workflow_event_history` VALUES ('8681a7eff5fc42b68bd99b12df110843', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_sczg', '2019-10-12', null, '6');
INSERT INTO `workflow_event_history` VALUES ('8681a7eff5fc42b68bd99b12df110843', 'g_gszc', 'g_gszc_node_sczg', 'g_gszc_node_cn', '2019-10-13', null, '3');
INSERT INTO `workflow_event_history` VALUES ('139eea231b1b48b3bccdd4b340584ad2', 'g_gjjdj', 'g_gjjdj_node_sq', 'g_gjjdj_node_sh', '2019-10-13', '2019-10-13', '1');
INSERT INTO `workflow_event_history` VALUES ('09b1660e997c42ce8e6b8a38af0b96d7', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-13', '2019-10-13', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('09b1660e997c42ce8e6b8a38af0b96d7', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-13', null, 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('8f6d74904e4a43899b0119766a1c2f5f', 'g_xkzbl', 'g_xkzbl_node_sq', 'g_xkzbl_node_sh', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('666984a26512497bbbe85cc96554794b', 'g_gjjdj', 'g_gjjdj_node_sq', 'g_gjjdj_node_sh', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('2c36c7db613e4a21b48ed16daa605203', 'g_gjjdj', 'g_gjjdj_node_sq', 'g_gjjdj_node_sh', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('0356576b6dad41169c53e158af871a63', 'g_gjjdk', 'g_gjjdk_node_sq', 'g_gjjdk_node_sh', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('a92c383c58134cf7bfde0da965d04303', 'g_gsbg', 'g_gsbg_node_sq', 'g_gsbg_node_wq', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('4615732a21f9446f9fcec8b9bd4fc8bd', 'g_gjjdk', 'g_gjjdk_node_sq', 'g_gjjdk_node_sh', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('b3dd4297235248538339a475164f5dfa', 'g_gjjdj', 'g_gjjdj_node_sq', 'g_gjjdj_node_sh', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('6f917efa7d414ef9b7bbc4ea385259fe', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-13', '2019-10-13', '2');
INSERT INTO `workflow_event_history` VALUES ('b3286a15d4604d73b64a636307cb9e6c', 'g_gszc', 'g_gszc_node_sczg', 'g_gszc_node_shzlsh', '2019-10-12', '2019-10-14', 'c377a64ceb244ce0a3174e75393f1820');
INSERT INTO `workflow_event_history` VALUES ('b3286a15d4604d73b64a636307cb9e6c', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_sczg', '2019-10-14', '2019-10-15', '6');
INSERT INTO `workflow_event_history` VALUES ('b3286a15d4604d73b64a636307cb9e6c', 'g_gszc', 'g_gszc_node_sczg', 'g_gszc_node_cn', '2019-10-15', '2019-10-15', '3');
INSERT INTO `workflow_event_history` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-15', '2019-10-15', '1');
INSERT INTO `workflow_event_history` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-15', '2019-10-15', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_shkhqz', '2019-10-15', '2019-10-15', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', 'g_gszc', 'g_gszc_node_shkhqz', 'g_gszc_node_shddxz', '2019-10-15', '2019-10-15', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', 'g_gszc', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_wqzgdyhkh', '2019-10-15', '2019-10-15', 'cdacf700cf484ee68163c5fcdf21906d');
INSERT INTO `workflow_event_history` VALUES ('9bbdcfabfbbd413b91f4d8c3f7cc5eee', 'g_gszc', 'g_gszc_node_ddxz_wqzgdyhkh', 'g_gszc_node_ddxz_wqyhkh', '2019-10-15', '2019-10-15', '894617b1ab9d4289a0192f6556579d03');
INSERT INTO `workflow_event_history` VALUES ('5398e437b12f4461b3b6d692e91d649a', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-16', '2019-10-16', '2');
INSERT INTO `workflow_event_history` VALUES ('c70378dc915742dca8fd5b77125b5159', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-17', '2019-10-17', '2');
INSERT INTO `workflow_event_history` VALUES ('c70378dc915742dca8fd5b77125b5159', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-17', '2019-10-17', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('c70378dc915742dca8fd5b77125b5159', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_shkhqz', '2019-10-17', '2019-10-17', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('c70378dc915742dca8fd5b77125b5159', 'g_gszc', 'g_gszc_node_shkhqz', 'g_gszc_node_shddxz', '2019-10-17', '2019-10-17', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('c70378dc915742dca8fd5b77125b5159', 'g_gszc', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_shyhkhzzkh', '2019-10-17', '2019-10-17', 'c377a64ceb244ce0a3174e75393f1820');
INSERT INTO `workflow_event_history` VALUES ('c70378dc915742dca8fd5b77125b5159', 'g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh', 'g_gszc_node_shyhkhzzkh_wqzgdhs', '2019-10-17', '2019-10-17', 'cdacf700cf484ee68163c5fcdf21906d');
INSERT INTO `workflow_event_history` VALUES ('6f41f3e311bc40b4af816db70daa0ce8', 'g_gszx', 'g_gszx_node_sq', 'g_gszx_node_wq', '2019-10-17', '2019-10-17', '2');
INSERT INTO `workflow_event_history` VALUES ('6f41f3e311bc40b4af816db70daa0ce8', 'g_gszx', 'g_gszx_node_wq', 'g_gszx_node_end', '2019-10-17', '2019-10-17', '');
INSERT INTO `workflow_event_history` VALUES ('f4982c5ef8574a5baa6f8021432181d5', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-17', '2019-10-17', '2');
INSERT INTO `workflow_event_history` VALUES ('f4982c5ef8574a5baa6f8021432181d5', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-17', '2019-10-17', 'a286d363052946d5a88b5e824c590540');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-17', '2019-10-17', '50ee1bda83f140eea692a78fa0b373e8');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-17', '2019-10-17', '3d2c42b03ead49dba7d65ddf1f854428');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_shkhqz', '2019-10-17', '2019-10-17', '3d2c42b03ead49dba7d65ddf1f854428');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_shkhqz', 'g_gszc_node_shddxz', '2019-10-17', '2019-10-17', '3d2c42b03ead49dba7d65ddf1f854428');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_shyhkhzzkh', '2019-10-17', '2019-10-17', '3d2c42b03ead49dba7d65ddf1f854428');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_ddxz_shyhkhzzkh', 'g_gszc_node_shyhkhzzkh_wqzgdhs', '2019-10-17', '2019-10-17', '9de1bb85ca9f434299ec2875cd8b5cde');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_shyhkhzzkh_wqzgdhs', 'g_gszc_node_shyhkhzzkh_wqhs', '2019-10-17', '2019-10-17', 'e2a08e2f5bfa484893702f224a5a6335');
INSERT INTO `workflow_event_history` VALUES ('2631c186c1cc4daaaf98f033bffbdc26', 'g_gszc', 'g_gszc_node_shyhkhzzkh_wqhs', 'g_gszc_node_end', '2019-10-17', '2019-10-17', '');
INSERT INTO `workflow_event_history` VALUES ('e80c2efa21c045fa9d275722372fc238', 'g_gszx', 'g_gszx_node_sq', 'g_gszx_node_wq', '2019-10-17', '2019-10-17', '50ee1bda83f140eea692a78fa0b373e8');
INSERT INTO `workflow_event_history` VALUES ('48ec0b235eed4c49868570959b95507f', 'g_gsbg', 'g_gsbg_node_sq', 'g_gsbg_node_wq', '2019-10-17', '2019-10-17', '50ee1bda83f140eea692a78fa0b373e8');
INSERT INTO `workflow_event_history` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-17', '2019-10-17', '50ee1bda83f140eea692a78fa0b373e8');
INSERT INTO `workflow_event_history` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_shzlsj', 'g_gszc_node_shzlsh', '2019-10-17', '2019-10-17', '3d2c42b03ead49dba7d65ddf1f854428');
INSERT INTO `workflow_event_history` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_shzlsh', 'g_gszc_node_shkhqz', '2019-10-17', '2019-10-17', '3d2c42b03ead49dba7d65ddf1f854428');
INSERT INTO `workflow_event_history` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_shkhqz', 'g_gszc_node_shddxz', '2019-10-17', '2019-10-17', '3d2c42b03ead49dba7d65ddf1f854428');
INSERT INTO `workflow_event_history` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_shddxz', 'g_gszc_node_ddxz_wqzgdyhkh', '2019-10-17', '2019-10-17', '9de1bb85ca9f434299ec2875cd8b5cde');
INSERT INTO `workflow_event_history` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_ddxz_wqzgdyhkh', 'g_gszc_node_ddxz_wqyhkh', '2019-10-17', '2019-10-17', '24f1228d56b743b399e1f8d4ec70fc67');
INSERT INTO `workflow_event_history` VALUES ('f306ed6dfb854cd9836ebbf7e3d6857d', 'g_gszc', 'g_gszc_node_ddxz_wqyhkh', 'g_gszc_node_shdhzfhs', '2019-10-17', '2019-10-17', '167390cb434c4d539c9ba1760ba1e901');
INSERT INTO `workflow_event_history` VALUES ('6ccaec607d5443fba4628a34f3f66ca2', 'g_sbzc', 'g_sbzc_node_sq', 'g_sbzc_node_sh', '2019-10-18', '2019-10-18', '7a8525b44a1e497db64eb0e092919d18');
INSERT INTO `workflow_event_history` VALUES ('d6c6d507a8e34d159af5a9342a2b5ea0', 'g_ycyw', 'g_ycyw_node_sq', 'g_ycyw_xcyccl', '2019-10-20', '2019-10-20', '7a8525b44a1e497db64eb0e092919d18');
INSERT INTO `workflow_event_history` VALUES ('e3e2fc2b51c74fca8e4ca228874e3344', 'g_ycyw', 'g_ycyw_node_sq', 'g_ycyw_xcyccl', '2019-10-20', '2019-10-20', '7a8525b44a1e497db64eb0e092919d18');
INSERT INTO `workflow_event_history` VALUES ('e3e2fc2b51c74fca8e4ca228874e3344', 'g_ycyw', 'g_ycyw_xcyccl', 'g_ycyw_xcjcyc', '2019-10-20', '2019-10-20', '15902a94b57648549216a8f2ed0765d7');
INSERT INTO `workflow_event_history` VALUES ('e3e2fc2b51c74fca8e4ca228874e3344', 'g_ycyw', 'g_ycyw_xcjcyc', 'g_ycyw_end', '2019-10-20', '2019-10-20', '24f1228d56b743b399e1f8d4ec70fc67');
INSERT INTO `workflow_event_history` VALUES ('f6ffbdecff5842839da26b5993bb2877', 'g_sbdk', 'g_sbdk_node_sq', 'g_sbdk_node_sh', '2019-10-21', '2019-10-21', '76562dba30ef49a0b8cffa04420ea7c5');
INSERT INTO `workflow_event_history` VALUES ('fedb91f6f42b45e68bf48adf11a7ab9c', 'g_gjjdk', 'g_gjjdk_node_sq', 'g_gjjdk_node_sh', '2019-10-21', '2019-10-21', '76562dba30ef49a0b8cffa04420ea7c5');
INSERT INTO `workflow_event_history` VALUES ('a8f03f88ac1942d5b33513c9b2f4a193', 'g_gszc', 'g_gszc_node_sq', 'g_gszc_node_shzlsj', '2019-10-21', '2019-10-21', '7a8525b44a1e497db64eb0e092919d18');
INSERT INTO `workflow_event_history` VALUES ('80707a12ccd84ebeb98da4a4f8c00786', 'g_xkzbl', 'g_xkzbl_node_sq', 'g_xkzbl_node_sh', '2019-10-21', '2019-10-21', '7a8525b44a1e497db64eb0e092919d18');
INSERT INTO `workflow_event_history` VALUES ('8db4305dd7a14abc91377620c2e3cfb5', 'g_xkzbl', 'g_xkzbl_node_sq', 'g_xkzbl_node_sh', '2019-10-22', '2019-10-22', '860f4bedb3f64010a92a90f08e8d0d05');
INSERT INTO `workflow_event_history` VALUES ('8db4305dd7a14abc91377620c2e3cfb5', 'g_xkzbl', 'g_xkzbl_node_sh', 'g_xkzbl_node_end', '2019-10-22', '2019-10-22', '167390cb434c4d539c9ba1760ba1e901');

-- ----------------------------
-- Table structure for workflow_log_attachment
-- ----------------------------
DROP TABLE IF EXISTS `workflow_log_attachment`;
CREATE TABLE `workflow_log_attachment` (
  `file_id` varchar(45) NOT NULL,
  `log_id` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `file_path` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程日志附件表';

-- ----------------------------
-- Records of workflow_log_attachment
-- ----------------------------
INSERT INTO `workflow_log_attachment` VALUES ('00036cbd2dd240d2978b39d2272aa16b', '3899e13af79c44b2be64573a3c3c23d5', '2019-09-26 00:56:23', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d//files');
INSERT INTO `workflow_log_attachment` VALUES ('00fbc94c179f49caa526be7ac6028905', '72973758874f488aaf444c76f6c10974', '2019-09-26 00:28:32', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820//files');
INSERT INTO `workflow_log_attachment` VALUES ('0568e2a1d66548f0b75a5f8759f7c992', 'da7bd365c0814ad38a0dd29c227ae732', '2019-10-12 14:26:16', 'http://119.3.19.171:80/workflow/log/attachment/8681a7eff5fc42b68bd99b12df110843/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('0cbebc25616f436ea998b08aeabd3fe6', '21d0183a90a249e29045fd620c0a0725', '2019-10-13 18:33:28', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('0fe0894b98334b2890e772a50a601739', '470b56043faf48c78ee819298c7242b9', '2019-09-26 00:05:37', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('1afbef33829a4bb38499f762e62519ee', 'c06751a00e3f4183b37f91936dc83dfe', '2019-09-26 00:54:43', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/894617b1ab9d4289a0192f6556579d03//files');
INSERT INTO `workflow_log_attachment` VALUES ('2f13effc26a34625b723f0d89d65903f', 'ebe1a47285194732807c973fa07df2b4', '2019-09-26 00:07:04', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('4580dd1f65fc470b9e533f5d62450503', 'de91700272764a7e8d294940a9a60af2', '2019-10-20 23:00:29', 'http://119.3.19.171:80/workflow/log/attachment/e3e2fc2b51c74fca8e4ca228874e3344/24f1228d56b743b399e1f8d4ec70fc67//files');
INSERT INTO `workflow_log_attachment` VALUES ('4cf5f00141fa49bdb8913c53658cbc50', '2841690411af4e7c9fe861266990eee3', '2019-10-17 16:38:39', 'http://119.3.19.171:80/workflow/log/attachment/f306ed6dfb854cd9836ebbf7e3d6857d/3d2c42b03ead49dba7d65ddf1f854428//files');
INSERT INTO `workflow_log_attachment` VALUES ('4d01c748721e48aa952f6f5204ecf96f', '1fb8aa5251e048a183d69892542cf3f1', '2019-10-11 22:24:36', 'http://119.3.19.171:80/workflow/log/attachment/74149bd10aef457d9f2ce1a7e3f28077/42ed651d283b4019aaab51117063b5f1//files');
INSERT INTO `workflow_log_attachment` VALUES ('519276be2d444e3db999f88ef896f06d', '16a577464ebf4621999392ceff8e4e82', '2019-09-26 00:09:45', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820//files');
INSERT INTO `workflow_log_attachment` VALUES ('5214823c51134b8eb51f812c1d1f2b8c', 'e478e3bf27e0485995367d2b526df897', '2019-10-12 14:34:11', 'http://119.3.19.171:80/workflow/log/attachment/8681a7eff5fc42b68bd99b12df110843/6//files');
INSERT INTO `workflow_log_attachment` VALUES ('5547cee9fc3548629580b61968ca8318', '872c5b526e7347b3aa28171d930a016f', '2019-09-25 23:44:28', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('5ffb6f92f57b41d497ef1e2bce4f9ca2', 'da5df017aaa54820af910ecf0c15f28c', '2019-09-25 23:48:48', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('61dced457ecf4302ae71fb036e508b11', '872c5b526e7347b3aa28171d930a016f', '2019-09-25 23:44:28', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('656666cfb6294d498a24e7d84ad4e268', 'df85816ab3a14722b41ea45f5f256995', '2019-10-11 22:16:19', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('71b848f855764eaa826ff8432cbaa9b3', '16a577464ebf4621999392ceff8e4e82', '2019-09-26 00:09:45', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820//files');
INSERT INTO `workflow_log_attachment` VALUES ('79d4d94e549045369a3063dc83415652', 'b40395532c1c417a938ecd14adaf7940', '2019-09-26 00:38:53', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d//files');
INSERT INTO `workflow_log_attachment` VALUES ('7a4ea80216474463b5dc672b4671a30a', '8474bc61fda544acbf1f195b8d5e8cee', '2019-10-13 18:31:59', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('7b84d3e242504689b4486d76b99b94a4', 'ebe1a47285194732807c973fa07df2b4', '2019-09-26 00:07:04', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('81766af46b554cb8a861a7af4fc9501c', '3210c932d3634ee2a0839fc0cf28c35a', '2019-10-17 13:56:32', 'http://119.3.19.171:80/workflow/log/attachment/f4982c5ef8574a5baa6f8021432181d5/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('82476585a37349bb9af6edababd62a2f', 'd0e17e116bc54b29bb4c3c880a72e673', '2019-09-26 00:08:46', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('85208d22b01147ee8bcce0f1c58ddee2', '6ad8131866c14e27b743d06aa7127f32', '2019-09-26 00:09:00', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('8f331cfe943c4891809fe7942101baea', '6b13c221e78c4797a7c5aa1b788a1764', '2019-10-22 00:08:49', 'http://119.3.19.171:80/workflow/log/attachment/8db4305dd7a14abc91377620c2e3cfb5/167390cb434c4d539c9ba1760ba1e901//files');
INSERT INTO `workflow_log_attachment` VALUES ('90bbd2feb5d545a8a7d476886bc0e15e', 'a63ad573b62c484995cfe14afeaf74ee', '2019-10-12 10:50:18', 'http://119.3.19.171:80/workflow/log/attachment/5b73e26c064f4e5db174cfd515952852/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('90d714d9b2934132b59a0918970c4081', '470b56043faf48c78ee819298c7242b9', '2019-09-26 00:05:37', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('9470b6e6137b4c6e84d1b388f4b3687e', '705863aa07b84b728d67b47ac62f1fad', '2019-10-17 21:47:40', 'http://119.3.19.171:80/workflow/log/attachment/f306ed6dfb854cd9836ebbf7e3d6857d/24f1228d56b743b399e1f8d4ec70fc67//files');
INSERT INTO `workflow_log_attachment` VALUES ('95cea610f08b4450b16cc0cae7f63375', '99a165bd249347f48dc11462af0f3ed3', '2019-10-17 22:49:29', 'http://119.3.19.171:80/workflow/log/attachment/f306ed6dfb854cd9836ebbf7e3d6857d/24f1228d56b743b399e1f8d4ec70fc67//files');
INSERT INTO `workflow_log_attachment` VALUES ('97b21b3e613a4f09ad68f1f5d087f35d', 'cf2993363fa74ff886a29dd59568fdbf', '2019-09-26 00:39:26', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d//files');
INSERT INTO `workflow_log_attachment` VALUES ('9ba96460f077472bb5599dada8196058', 'c06751a00e3f4183b37f91936dc83dfe', '2019-09-26 00:54:43', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/894617b1ab9d4289a0192f6556579d03//files');
INSERT INTO `workflow_log_attachment` VALUES ('9df6f3d34b1d4fad9f7adb8631679577', 'c431cf40e547463eab10165c18b57363', '2019-10-11 22:16:44', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('a064de9976f64f4fa80d0e97d3849f70', '232daeefa40348a2986ab99b2df53598', '2019-10-12 12:00:30', 'http://119.3.19.171:80/workflow/log/attachment/5b73e26c064f4e5db174cfd515952852/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('a24a6182f81d4b30957b796981ec6291', '8bdf48d3130943ebb29e348dd80dbc0f', '2019-10-13 18:32:33', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('aa353c6e637940b98fbb3e91525b274f', 'c18e54d0bfb2401d91026d35cafc2dbc', '2019-10-17 13:16:45', 'http://119.3.19.171:80/workflow/log/attachment/c70378dc915742dca8fd5b77125b5159/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('aaba33c55c1247dea20968f063b709f9', 'a6a5377ef2e146388c113c0ddc7f7124', '2019-10-11 01:43:46', 'http://119.3.19.171:80/workflow/log/attachment/a397a04b1ab042a589e322c23bc6ef94/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('ace9907ec1d44ef194af784ec54a63d6', 'bd9027054b09483b84d8ffdbcd69540a', '2019-10-12 14:26:39', 'http://119.3.19.171:80/workflow/log/attachment/8681a7eff5fc42b68bd99b12df110843/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('ad1a0d2088d54eba96df3c61154acefd', '5af788e35c614e62a186e2f2aa37094c', '2019-10-17 13:21:05', 'http://119.3.19.171:80/workflow/log/attachment/c70378dc915742dca8fd5b77125b5159/c377a64ceb244ce0a3174e75393f1820//files');
INSERT INTO `workflow_log_attachment` VALUES ('af6f3fdb151c42d58f7e2b717089408d', 'd7a758ccdf964432a426d7d1775074eb', '2019-09-26 00:56:50', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d//files');
INSERT INTO `workflow_log_attachment` VALUES ('b4512de3a0564be88e38c13994dab6b5', '5275d40f3ec84fd6b4cd31cf667dd2ce', '2019-09-26 01:27:28', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/42ed651d283b4019aaab51117063b5f1//files');
INSERT INTO `workflow_log_attachment` VALUES ('bcedd6d1b29546f38c27bc89c835e4db', 'c18e9ad07a5e4e39a737846006d985d8', '2019-09-26 00:30:00', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820//files');
INSERT INTO `workflow_log_attachment` VALUES ('c754830db5034ddba4adb1df2b13c3a3', 'da5df017aaa54820af910ecf0c15f28c', '2019-09-25 23:48:48', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('d500cbb21ed8445ea9f56bd4241d62ae', '007863a1dc384407a0879f59ff255c7d', '2019-10-17 15:04:12', 'http://119.3.19.171:80/workflow/log/attachment/2631c186c1cc4daaaf98f033bffbdc26/3d2c42b03ead49dba7d65ddf1f854428//files');
INSERT INTO `workflow_log_attachment` VALUES ('d9bb260abf464ea69cc6685067139ac8', '72973758874f488aaf444c76f6c10974', '2019-09-26 00:28:32', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820//files');
INSERT INTO `workflow_log_attachment` VALUES ('dc8caeb1d2454fd0b7d76570d4c8bc5d', '872c5b526e7347b3aa28171d930a016f', '2019-09-25 23:44:28', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('dce0a1d1d7de4f12ae7547e221849761', 'b5a1eac434604547b38e6d5efd7c5438', '2019-10-11 22:18:50', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('ee28ad07d3614cfb899145cff0a45958', 'b40395532c1c417a938ecd14adaf7940', '2019-09-26 00:38:53', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d//files');
INSERT INTO `workflow_log_attachment` VALUES ('f5a4c55056df413a924c74b3c4ed0c0b', '9914cf718d474665bbc3eab70610c2b1', '2019-10-11 22:17:48', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('f6f30d17328a413d875a33a6e7ef36fe', '94fb68dd730948459871d3b30908ee83', '2019-10-13 18:30:01', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('fd3083ac968b4beab82d239f27752d4d', '175fa0b1807b4d73b84bc45e825c4285', '2019-10-12 10:49:53', 'http://119.3.19.171:80/workflow/log/attachment/5b73e26c064f4e5db174cfd515952852/a286d363052946d5a88b5e824c590540//files');
INSERT INTO `workflow_log_attachment` VALUES ('ff8d8723e4464775b9972f0257f39b4b', '1424c0ddf2de44238c58c69f9423ed99', '2019-09-25 23:36:56', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540//files');

-- ----------------------------
-- Table structure for workflow_log_message
-- ----------------------------
DROP TABLE IF EXISTS `workflow_log_message`;
CREATE TABLE `workflow_log_message` (
  `log_id` varchar(45) NOT NULL,
  `operator_name` varchar(45) DEFAULT NULL,
  `operator` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `event_id` varchar(45) DEFAULT NULL,
  `has_attachment` varchar(1) DEFAULT '0',
  `attachments_path` varchar(400) DEFAULT NULL,
  `has_refund` varchar(1) DEFAULT NULL,
  `refund_fee` decimal(20,5) DEFAULT NULL,
  `node_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程日志表';

-- ----------------------------
-- Records of workflow_log_message
-- ----------------------------
INSERT INTO `workflow_log_message` VALUES ('007863a1dc384407a0879f59ff255c7d', '陈峰', '3d2c42b03ead49dba7d65ddf1f854428', '2019-10-17 15:04:12', '资料已收齐，提交园区处理', '2631c186c1cc4daaaf98f033bffbdc26', '1', 'http://119.3.19.171:80/workflow/log/attachment/2631c186c1cc4daaaf98f033bffbdc26/3d2c42b03ead49dba7d65ddf1f854428/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('013eb39f543e4ecea917c0c80506267d', 'ADMIN', '1', '2019-10-12 14:28:15', '订单号: 1231231,业务类型: 公司注册,当前节点: 退款审批,原处理人: 我是市场主管1,变更处理人: 我是市场主管2', 'b3286a15d4604d73b64a636307cb9e6c', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('0af3ee44129a477fa9bdc62cc0de7679', '我是售前1', '2', '2019-09-26 03:05:01', '我是马云，明人不说暗话，我是你爸爸', '6550e24c4bed45dc9c59e42c1ec681d9', '0', null, null, null, 'g_gsbg_node_sq');
INSERT INTO `workflow_log_message` VALUES ('0b471a34e1c84c058dbfd984d7ca5256', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-17 13:18:35', '等待下证', 'c70378dc915742dca8fd5b77125b5159', '0', null, null, null, 'g_gszc_node_shkhqz');
INSERT INTO `workflow_log_message` VALUES ('1233', '我是主管1', '8', '2019-09-08 15:29:10', '马云爸爸答应给我们投资个小目标', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, '', null, null);
INSERT INTO `workflow_log_message` VALUES ('13ed56e86953402fa7eb2ad24a49124d', '我是售前1', '2', '2019-10-12 10:48:33', 'sdfsa', '65792f61524048d393a96fc6a1311d37', '0', null, null, null, 'g_gjjdj_node_sq');
INSERT INTO `workflow_log_message` VALUES ('1424c0ddf2de44238c58c69f9423ed99', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-25 23:36:56', '请尽快收集资料', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('15bf7ad14c9b4d6396e7c594a43ba6ca', '蒋志霞', '7a8525b44a1e497db64eb0e092919d18', '2019-10-18 15:29:33', '老客户推荐', '6ccaec607d5443fba4628a34f3f66ca2', '0', null, null, null, 'g_sbzc_node_sq');
INSERT INTO `workflow_log_message` VALUES ('16a577464ebf4621999392ceff8e4e82', '我是售后2', 'c377a64ceb244ce0a3174e75393f1820', '2019-09-26 00:09:45', '证书已经拿到', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('175fa0b1807b4d73b84bc45e825c4285', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-12 10:49:53', '123', '5b73e26c064f4e5db174cfd515952852', '1', 'http://119.3.19.171:80/workflow/log/attachment/5b73e26c064f4e5db174cfd515952852/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('1fb8aa5251e048a183d69892542cf3f1', '我是外勤2', '42ed651d283b4019aaab51117063b5f1', '2019-10-11 22:24:36', null, '74149bd10aef457d9f2ce1a7e3f28077', '1', 'http://119.3.19.171:80/workflow/log/attachment/74149bd10aef457d9f2ce1a7e3f28077/42ed651d283b4019aaab51117063b5f1/', null, null, 'g_gszx_node_wq');
INSERT INTO `workflow_log_message` VALUES ('21d0183a90a249e29045fd620c0a0725', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-13 18:33:28', '啊啊啊', '09b1660e997c42ce8e6b8a38af0b96d7', '1', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('232daeefa40348a2986ab99b2df53598', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-12 12:00:30', '124124124', '5b73e26c064f4e5db174cfd515952852', '1', 'http://119.3.19.171:80/workflow/log/attachment/5b73e26c064f4e5db174cfd515952852/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shkhqz');
INSERT INTO `workflow_log_message` VALUES ('2841690411af4e7c9fe861266990eee3', '陈峰', '3d2c42b03ead49dba7d65ddf1f854428', '2019-10-17 16:38:39', null, 'f306ed6dfb854cd9836ebbf7e3d6857d', '1', 'http://119.3.19.171:80/workflow/log/attachment/f306ed6dfb854cd9836ebbf7e3d6857d/3d2c42b03ead49dba7d65ddf1f854428/', null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('3011aec2364446c6ab11d93d774b0d18', '我是售前1', '2', '2019-10-13 23:47:45', '公积金代缴，请立即执行', 'b3dd4297235248538339a475164f5dfa', '0', null, null, null, 'g_gjjdj_node_sq');
INSERT INTO `workflow_log_message` VALUES ('3071448d517d4c2d8bcfd245f2a16621', '我是市场主管1', '6', '2019-10-14 00:03:21', '延期2天,需要延时2天', 'b3286a15d4604d73b64a636307cb9e6c', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('3210c932d3634ee2a0839fc0cf28c35a', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-17 13:56:32', '收集客户资料提交至园区', 'f4982c5ef8574a5baa6f8021432181d5', '1', 'http://119.3.19.171:80/workflow/log/attachment/f4982c5ef8574a5baa6f8021432181d5/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('32ce34d7cb0c475eb58cbbf12ed62c12', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-17 13:19:25', '客户嫌建行太远，自行选择就近银行开户', 'c70378dc915742dca8fd5b77125b5159', '0', null, null, null, 'g_gszc_node_shddxz');
INSERT INTO `workflow_log_message` VALUES ('3899e13af79c44b2be64573a3c3c23d5', '我是外勤主管', 'cdacf700cf484ee68163c5fcdf21906d', '2019-09-26 00:56:23', '待银行开户', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d/', null, null, 'g_gszc_node_hs_wqzgdyhkh');
INSERT INTO `workflow_log_message` VALUES ('3acef0fb924b4a9a8640660f55af41fa', '陈东宏', '860f4bedb3f64010a92a90f08e8d0d05', '2019-10-22 00:07:47', '许可证办理测试：', '8db4305dd7a14abc91377620c2e3cfb5', '0', null, null, null, 'g_xkzbl_node_sq');
INSERT INTO `workflow_log_message` VALUES ('41417204e07245dbb786fd2da4dc0bf8', 'ADMIN', '1', '2019-10-12 14:29:56', '啊说法,需要延时30天', 'b3286a15d4604d73b64a636307cb9e6c', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('43ccac6781a540eeb9adcce11b1467a5', '我是售后2', 'c377a64ceb244ce0a3174e75393f1820', '2019-10-15 00:14:38', '必须退款', 'b3286a15d4604d73b64a636307cb9e6c', '0', null, null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('45d8a3daddd545129ef14e8142d87179', '我是市场主管2', '7', '2019-10-14 23:01:28', '不同意退款', 'b3286a15d4604d73b64a636307cb9e6c', '0', null, null, null, 'g_gszc_node_sczg');
INSERT INTO `workflow_log_message` VALUES ('470b56043faf48c78ee819298c7242b9', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-26 00:05:37', '资料收集完毕，请开始资料审核', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('47be3cefb971414093f9fd36a066aef1', '我是售前1', '2', '2019-10-16 23:10:06', '测试：公司注册，挺着急的', '5398e437b12f4461b3b6d692e91d649a', '0', null, null, null, 'g_gszc_node_sq');
INSERT INTO `workflow_log_message` VALUES ('48cc35d760ad439e986c741e7b01cb82', '我是市场主管1', '6', '2019-10-13 23:58:47', '改员工请假，延期两天,需要延时2天', 'ea47292a2b3f436fa38650e6b496a367', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('4ceae535ec7b4d58bb1117dbe26e56b9', '我是售前1', '2', '2019-10-17 13:55:37', '烁翼付款 嘉定小规模套餐 旺旺：cbv2000se1 陈东,18516608340 我直接拉群 @企账云-售后陈陈 对接@陈翌路 ', 'f4982c5ef8574a5baa6f8021432181d5', '0', null, null, null, 'g_gszc_node_sq');
INSERT INTO `workflow_log_message` VALUES ('4fff69c0745e487c898cebd9c2a48e85', '我是财务主管1', '4', '2019-10-12 11:43:02', '订单号: 12431431,业务类型: 公积金代缴,当前节点: 公积金代缴,原处理人: 我是售后2,变更处理人: 我是售后_1', '65792f61524048d393a96fc6a1311d37', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('5275d40f3ec84fd6b4cd31cf667dd2ce', '我是外勤2', '42ed651d283b4019aaab51117063b5f1', '2019-09-26 01:27:28', '结束流程', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/42ed651d283b4019aaab51117063b5f1/', null, null, 'g_gszc_node_hs_wqyhkh');
INSERT INTO `workflow_log_message` VALUES ('5549102fc19f4ca19556148d1bd9c0bc', '我是市场主管1', '6', '2019-10-13 16:36:49', '同意退款', '8681a7eff5fc42b68bd99b12df110843', '0', null, null, null, 'g_gszc_node_sczg');
INSERT INTO `workflow_log_message` VALUES ('57a4999e89c34218b00b447d9738012d', '我是售前1', '2', '2019-09-26 03:13:25', 'WOSSJFOSJ', '021a3c942b07499ea99c4b6cd8cd0523', '0', null, null, null, 'g_gszx_node_sq');
INSERT INTO `workflow_log_message` VALUES ('59085384dfaf454f9a6a99b314474143', '我是财务主管1', '4', '2019-10-12 10:25:38', '订单号: 12431431,业务类型: 公司注册,当前节点: 退款审批,原处理人: 我是市场主管2,变更处理人: 我是市场主管1', 'b3286a15d4604d73b64a636307cb9e6c', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('5af788e35c614e62a186e2f2aa37094c', '我是售后2', 'c377a64ceb244ce0a3174e75393f1820', '2019-10-17 13:21:05', '核税', 'c70378dc915742dca8fd5b77125b5159', '1', 'http://119.3.19.171:80/workflow/log/attachment/c70378dc915742dca8fd5b77125b5159/c377a64ceb244ce0a3174e75393f1820/', null, null, 'g_gszc_node_ddxz_shyhkhzzkh');
INSERT INTO `workflow_log_message` VALUES ('5b133fcc63744e76aaed88c84964ceb4', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-26 00:07:50', '资料审核完成', 'e2fb75f5b0674b139c34191384a987ef', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('5c260f53aa9046c8bfb3bce2fcaa36d7', '我是财务主管1', '4', '2019-10-12 11:46:25', '订单号: 12431431,业务类型: 公积金代缴,当前节点: 公积金代缴,原处理人: 我是售后_1,变更处理人: 我是售后2', '65792f61524048d393a96fc6a1311d37', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('5c5dac4b292d472eb5828477173229a7', '我是售前1', '2', '2019-10-13 20:14:13', '124', '2c36c7db613e4a21b48ed16daa605203', '0', null, null, null, 'g_gjjdj_node_sq');
INSERT INTO `workflow_log_message` VALUES ('6ad8131866c14e27b743d06aa7127f32', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-26 00:09:00', '客户签字完成', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('6b13c221e78c4797a7c5aa1b788a1764', '魏稳根', '167390cb434c4d539c9ba1760ba1e901', '2019-10-22 00:08:49', '可以办理许可证', '8db4305dd7a14abc91377620c2e3cfb5', '1', 'http://119.3.19.171:80/workflow/log/attachment/8db4305dd7a14abc91377620c2e3cfb5/167390cb434c4d539c9ba1760ba1e901/', null, null, 'g_xkzbl_node_sh');
INSERT INTO `workflow_log_message` VALUES ('6cada6cebd484b80a403a14385624fbc', '我是售前1', '2', '2019-10-13 19:07:45', '21', '666984a26512497bbbe85cc96554794b', '0', null, null, null, 'g_gjjdj_node_sq');
INSERT INTO `workflow_log_message` VALUES ('705863aa07b84b728d67b47ac62f1fad', '田国庆', '24f1228d56b743b399e1f8d4ec70fc67', '2019-10-17 21:47:40', null, 'f306ed6dfb854cd9836ebbf7e3d6857d', '1', 'http://119.3.19.171:80/workflow/log/attachment/f306ed6dfb854cd9836ebbf7e3d6857d/24f1228d56b743b399e1f8d4ec70fc67/', null, null, 'g_gszc_node_ddxz_wqyhkh');
INSERT INTO `workflow_log_message` VALUES ('72973758874f488aaf444c76f6c10974', '我是售后2', 'c377a64ceb244ce0a3174e75393f1820', '2019-09-26 00:28:32', '证书已经到位', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820/', null, null, 'g_gszc_node_shddxz');
INSERT INTO `workflow_log_message` VALUES ('7da7f407e14f444d8087bef1e2ee4766', '我是售前1', '2', '2019-10-13 23:52:45', '公司注册，请立即执行', '6f917efa7d414ef9b7bbc4ea385259fe', '0', null, null, null, 'g_gszc_node_sq');
INSERT INTO `workflow_log_message` VALUES ('7f176908d530466393df21b49fabef73', '我是财务主管1', '4', '2019-10-12 10:25:16', '订单号: 12431431,业务类型: 工商注销,当前节点: 公司注销,原处理人: 我是外勤2,变更处理人: 我是外勤1', '021a3c942b07499ea99c4b6cd8cd0523', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('7f60db17097b42c39aa275d53f4aeb1f', '我是售前1', '2', '2019-10-13 20:40:43', '1243', '0356576b6dad41169c53e158af871a63', '0', null, null, null, 'g_gjjdk_node_sq');
INSERT INTO `workflow_log_message` VALUES ('81f78d805baa4b2a94dea82c2483359b', '我是售前1', '2', '2019-10-13 19:06:01', '许可证办理', '8f6d74904e4a43899b0119766a1c2f5f', '0', null, null, null, 'g_xkzbl_node_sq');
INSERT INTO `workflow_log_message` VALUES ('831d20f7251e457ead6343dcda0db763', '蒋志霞', '7a8525b44a1e497db64eb0e092919d18', '2019-10-20 21:34:11', '异常业务测试：挺着急的', 'd6c6d507a8e34d159af5a9342a2b5ea0', '0', null, null, null, 'g_ycyw_node_sq');
INSERT INTO `workflow_log_message` VALUES ('8474bc61fda544acbf1f195b8d5e8cee', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-13 18:31:59', '123131', '09b1660e997c42ce8e6b8a38af0b96d7', '1', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('872c5b526e7347b3aa28171d930a016f', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-25 23:44:28', '请尽快收集资料', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('888a835cdbcb419eac9da146626be731', '我是主管1', '8', '2019-09-17 00:35:13', '不同意退款，请老总批示', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('8bdf48d3130943ebb29e348dd80dbc0f', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-13 18:32:33', '123123', '09b1660e997c42ce8e6b8a38af0b96d7', '1', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('916f5acdc500438a8981a83f66db2ee1', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-12 11:04:35', '客户已经签字', '5b73e26c064f4e5db174cfd515952852', '0', null, null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('94fb68dd730948459871d3b30908ee83', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-13 18:30:01', '123123', '09b1660e997c42ce8e6b8a38af0b96d7', '1', 'http://119.3.19.171:80/workflow/log/attachment/09b1660e997c42ce8e6b8a38af0b96d7/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('9914cf718d474665bbc3eab70610c2b1', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-11 22:17:48', null, 'b3286a15d4604d73b64a636307cb9e6c', '1', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('99a165bd249347f48dc11462af0f3ed3', '田国庆', '24f1228d56b743b399e1f8d4ec70fc67', '2019-10-17 22:49:29', '待合作方核税', 'f306ed6dfb854cd9836ebbf7e3d6857d', '1', 'http://119.3.19.171:80/workflow/log/attachment/f306ed6dfb854cd9836ebbf7e3d6857d/24f1228d56b743b399e1f8d4ec70fc67/', null, null, 'g_gszc_node_ddxz_wqyhkh');
INSERT INTO `workflow_log_message` VALUES ('99d2e41d23d5428bbc79c74a66730665', '我是财务主管1', '4', '2019-10-12 10:25:27', '订单号: 12431431,业务类型: 工商注销,当前节点: 公司注销,原处理人: 我是外勤1,变更处理人: 我是外勤2', '021a3c942b07499ea99c4b6cd8cd0523', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('9cfe2961fbb643b1833a704534700574', '我是市场主管1', '6', '2019-10-15 00:21:28', '同意退款', 'b3286a15d4604d73b64a636307cb9e6c', '0', null, null, null, 'g_gszc_node_sczg');
INSERT INTO `workflow_log_message` VALUES ('9ea60cdbcb484b13a055ce779f4a4182', '我是售后2', 'c377a64ceb244ce0a3174e75393f1820', '2019-10-09 22:29:31', '请立即退款', 'e9a1be1141a34e9cb07fbbc2d9d2f23f', '0', null, null, null, 'g_gjjdk_node_sh');
INSERT INTO `workflow_log_message` VALUES ('a55347d85230447a8a0892cc752f8227', '我是主管1', '8', '2019-09-15 19:32:28', '这个案子处理过程很复杂,需要延时10天', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('a5a9ec81f25d47b98e0e849fc293deb6', '张强强', '15902a94b57648549216a8f2ed0765d7', '2019-10-20 22:30:46', '1234', 'e3e2fc2b51c74fca8e4ca228874e3344', '0', null, null, null, 'g_ycyw_xcyccl');
INSERT INTO `workflow_log_message` VALUES ('a63ad573b62c484995cfe14afeaf74ee', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-12 10:50:18', '214', '5b73e26c064f4e5db174cfd515952852', '1', 'http://119.3.19.171:80/workflow/log/attachment/5b73e26c064f4e5db174cfd515952852/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('a6a5377ef2e146388c113c0ddc7f7124', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-11 01:43:46', 'sgsgss', 'a397a04b1ab042a589e322c23bc6ef94', '1', 'http://119.3.19.171:80/workflow/log/attachment/a397a04b1ab042a589e322c23bc6ef94/a286d363052946d5a88b5e824c590540/', null, null, 'g_xkzbl_node_sh');
INSERT INTO `workflow_log_message` VALUES ('a6fbfd39cf014b6ebc5b8e9c55e62574', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-17 13:18:04', '无需到场签字', 'c70378dc915742dca8fd5b77125b5159', '0', null, null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('abe56a772cc94e83bee3c7a1c56a3daf', '我是售前1', '2', '2019-09-26 03:23:36', '大概多少搞定', 'e9a1be1141a34e9cb07fbbc2d9d2f23f', '0', null, null, null, 'g_gjjdk_node_sq');
INSERT INTO `workflow_log_message` VALUES ('ad411d06aea4453fa004878d2e9e6229', 'ADMIN', '1', '2019-10-09 22:14:52', '延期1天,需要延时1天', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('ae1ff7d06dde49b6961c1cd8291a43e8', '我是市场主管1', '6', '2019-10-14 00:01:02', '延期2天,需要延时2天', 'ea47292a2b3f436fa38650e6b496a367', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('b40395532c1c417a938ecd14adaf7940', '我是外勤主管', 'cdacf700cf484ee68163c5fcdf21906d', '2019-09-26 00:38:53', '核税完成', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d/', null, null, 'g_gszc_node_ddxz_wqzgdhs');
INSERT INTO `workflow_log_message` VALUES ('b5a1eac434604547b38e6d5efd7c5438', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-11 22:18:50', '111', 'b3286a15d4604d73b64a636307cb9e6c', '1', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('b61576a0c21548729bf1a15fa2d5d85d', '我是售前1', '2', '2019-10-13 20:42:31', '124124', 'a92c383c58134cf7bfde0da965d04303', '0', null, null, null, 'g_gsbg_node_sq');
INSERT INTO `workflow_log_message` VALUES ('bb7494aca73f4966a6a78febeb590289', '我是财务主管1', '4', '2019-10-12 11:37:32', '订单号: 12431431,业务类型: 公积金代缴,当前节点: 公积金代缴,原处理人: 我是售后_1,变更处理人: 我是售后2', '65792f61524048d393a96fc6a1311d37', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('bd7b02c0f8b5438d87bcad527f96ed59', '我是市场主管1', '6', '2019-10-14 00:01:55', '延期3天,需要延时3天', 'ea47292a2b3f436fa38650e6b496a367', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('bd9027054b09483b84d8ffdbcd69540a', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-12 14:26:39', '·····', '8681a7eff5fc42b68bd99b12df110843', '1', 'http://119.3.19.171:80/workflow/log/attachment/8681a7eff5fc42b68bd99b12df110843/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('c06751a00e3f4183b37f91936dc83dfe', '我是外勤1', '894617b1ab9d4289a0192f6556579d03', '2019-09-26 00:54:43', '核税完成', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/894617b1ab9d4289a0192f6556579d03/', null, null, 'g_gszc_node_hs_wqzgdyhkh');
INSERT INTO `workflow_log_message` VALUES ('c08417cd139945e2a804d16130a39466', '我是售前1', '2', '2019-10-12 10:36:21', '2rfwef', '5b73e26c064f4e5db174cfd515952852', '0', null, null, null, 'g_gszc_node_sq');
INSERT INTO `workflow_log_message` VALUES ('c18e54d0bfb2401d91026d35cafc2dbc', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-17 13:16:45', '微信群已建立，收集公司注册所需的客户资料', 'c70378dc915742dca8fd5b77125b5159', '1', 'http://119.3.19.171:80/workflow/log/attachment/c70378dc915742dca8fd5b77125b5159/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('c18e9ad07a5e4e39a737846006d985d8', '我是售后2', 'c377a64ceb244ce0a3174e75393f1820', '2019-09-26 00:30:00', '下证完成，请核税', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/c377a64ceb244ce0a3174e75393f1820/', null, null, 'g_gszc_node_ddxz_wqzgdhs');
INSERT INTO `workflow_log_message` VALUES ('c2acd1d9f60b47fb8fd2360809fd0d57', '我是财务主管1', '4', '2019-10-12 11:44:46', '订单号: 12431431,业务类型: 公积金代缴,当前节点: 公积金代缴,原处理人: 我是售后2,变更处理人: 我是售后_1', '65792f61524048d393a96fc6a1311d37', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('c3b54bde91bd468081e5f2738fd437d6', '刘凯利', '50ee1bda83f140eea692a78fa0b373e8', '2019-10-17 15:02:05', '烁翼付款 嘉定小规模套餐 旺旺：cbv2000se1 陈东,18516608340 我直接拉群', '2631c186c1cc4daaaf98f033bffbdc26', '0', null, null, null, 'g_gszc_node_sq');
INSERT INTO `workflow_log_message` VALUES ('c431cf40e547463eab10165c18b57363', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-11 22:16:44', '111', 'b3286a15d4604d73b64a636307cb9e6c', '1', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('c81d84c6b573486bbd3dba15efa47f3e', '陈婷婷', 'e2a08e2f5bfa484893702f224a5a6335', '2019-10-17 15:19:45', '已核税。', '2631c186c1cc4daaaf98f033bffbdc26', '0', null, null, null, 'g_gszc_node_shyhkhzzkh_wqhs');
INSERT INTO `workflow_log_message` VALUES ('cf2993363fa74ff886a29dd59568fdbf', '我是外勤主管', 'cdacf700cf484ee68163c5fcdf21906d', '2019-09-26 00:39:26', '去核税', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d/', null, null, 'g_gszc_node_ddxz_wqhs');
INSERT INTO `workflow_log_message` VALUES ('d013cb7170154aa182799a825e1f66b1', '蒋志霞', '7a8525b44a1e497db64eb0e092919d18', '2019-10-21 11:27:33', '危化品', '80707a12ccd84ebeb98da4a4f8c00786', '0', null, null, null, 'g_xkzbl_node_sq');
INSERT INTO `workflow_log_message` VALUES ('d0e17e116bc54b29bb4c3c880a72e673', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-26 00:08:46', '客户签字完成', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('d62098fd55524aafaa3cd42f1c10d2f8', '我是主管1', '8', '2019-09-17 00:39:16', '不同意退款，请老总批示', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('d7a758ccdf964432a426d7d1775074eb', '我是外勤主管', 'cdacf700cf484ee68163c5fcdf21906d', '2019-09-26 00:56:50', '去银行开户', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/cdacf700cf484ee68163c5fcdf21906d/', null, null, 'g_gszc_node_hs_wqzgdyhkh');
INSERT INTO `workflow_log_message` VALUES ('da5df017aaa54820af910ecf0c15f28c', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-25 23:48:48', '请尽快收集资料', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('da7bd365c0814ad38a0dd29c227ae732', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-12 14:26:16', '阿斯顿法定', '8681a7eff5fc42b68bd99b12df110843', '1', 'http://119.3.19.171:80/workflow/log/attachment/8681a7eff5fc42b68bd99b12df110843/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('dbed9606178240a6906d3bfc6a102195', '我是售前1', '2', '2019-09-26 03:19:28', '多少过水电费', 'a397a04b1ab042a589e322c23bc6ef94', '0', null, null, null, 'g_xkzbl_node_sq');
INSERT INTO `workflow_log_message` VALUES ('de91700272764a7e8d294940a9a60af2', '田国庆', '24f1228d56b743b399e1f8d4ec70fc67', '2019-10-20 23:00:29', '5678', 'e3e2fc2b51c74fca8e4ca228874e3344', '1', 'http://119.3.19.171:80/workflow/log/attachment/e3e2fc2b51c74fca8e4ca228874e3344/24f1228d56b743b399e1f8d4ec70fc67/', null, null, 'g_ycyw_xcjcyc');
INSERT INTO `workflow_log_message` VALUES ('df85816ab3a14722b41ea45f5f256995', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-10-11 22:16:19', '撒发射点发', 'b3286a15d4604d73b64a636307cb9e6c', '1', 'http://119.3.19.171:80/workflow/log/attachment/b3286a15d4604d73b64a636307cb9e6c/a286d363052946d5a88b5e824c590540/', null, null, 'g_gszc_node_shzlsj');
INSERT INTO `workflow_log_message` VALUES ('e1c859e5457d4944a4cb96b58d7de794', 'ADMIN', '1', '2019-10-12 12:14:29', '阿萨法发', 'd74393ec477d40f788ef308f0189c246', '0', null, null, null, 'g_xkzbl_node_sq');
INSERT INTO `workflow_log_message` VALUES ('e3b098be9993442587a11a6db73986f8', '田国庆', '24f1228d56b743b399e1f8d4ec70fc67', '2019-10-20 23:01:09', '00000', 'e3e2fc2b51c74fca8e4ca228874e3344', '0', null, null, null, 'g_ycyw_xcjcyc');
INSERT INTO `workflow_log_message` VALUES ('e478e3bf27e0485995367d2b526df897', '我是市场主管1', '6', '2019-10-12 14:34:11', '说法地方', '8681a7eff5fc42b68bd99b12df110843', '1', 'http://119.3.19.171:80/workflow/log/attachment/8681a7eff5fc42b68bd99b12df110843/6/', null, null, 'g_gszc_node_sczg');
INSERT INTO `workflow_log_message` VALUES ('e6a503ca3aa24afd82ee7e7661bab9d4', 'ADMIN', '1', '2019-10-12 12:15:18', '萨芬士大夫', '7b1edf23d02d4f0185439ae87b4416b8', '0', null, null, null, 'g_sbdj_node_sq');
INSERT INTO `workflow_log_message` VALUES ('e77dfb3f881b4cebb0155effd233b009', '我是售前1', '2', '2019-10-13 20:45:57', '1414', '4615732a21f9446f9fcec8b9bd4fc8bd', '0', null, null, null, 'g_gjjdk_node_sq');
INSERT INTO `workflow_log_message` VALUES ('ea828457fbb741d7b4d1541268389339', '蒋志霞', '7a8525b44a1e497db64eb0e092919d18', '2019-10-20 21:49:45', '异常业务测试：你看着办吧', 'e3e2fc2b51c74fca8e4ca228874e3344', '0', null, null, null, 'g_ycyw_node_sq');
INSERT INTO `workflow_log_message` VALUES ('ebe1a47285194732807c973fa07df2b4', '我是售后_1', 'a286d363052946d5a88b5e824c590540', '2019-09-26 00:07:04', '资料审核完成，客户资质优良', 'e2fb75f5b0674b139c34191384a987ef', '1', 'http://119.3.19.171:80/workflow/log/attachment/e2fb75f5b0674b139c34191384a987ef/a286d363052946d5a88b5e824c590540/', null, null, null);
INSERT INTO `workflow_log_message` VALUES ('f4d6f2c816e94795b6159ee7513a62d6', '魏稳根', '167390cb434c4d539c9ba1760ba1e901', '2019-10-22 00:09:15', '结束办理，办理成功', '8db4305dd7a14abc91377620c2e3cfb5', '0', null, null, null, 'g_xkzbl_node_sh');
INSERT INTO `workflow_log_message` VALUES ('f6ae1fd12c3e4bc2934abe3375cbe8b6', '我是财务主管1', '4', '2019-10-12 10:24:05', '订单号: 12431431,业务类型: 工商注销,当前节点: 公司注销,原处理人: 我是外勤1,变更处理人: 我是外勤2', '021a3c942b07499ea99c4b6cd8cd0523', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('f6c9698daf1a49d3b6d33edca97013c8', '刘凯利', '50ee1bda83f140eea692a78fa0b373e8', '2019-10-17 16:35:33', '崇明小规模套餐', 'f306ed6dfb854cd9836ebbf7e3d6857d', '0', null, null, null, 'g_gszc_node_sq');
INSERT INTO `workflow_log_message` VALUES ('f8251c0dc69640cab737f99a8d54648b', '陈峰', '3d2c42b03ead49dba7d65ddf1f854428', '2019-10-17 15:09:20', '资料园区已核实，客户网签。', '2631c186c1cc4daaaf98f033bffbdc26', '0', null, null, null, 'g_gszc_node_shzlsh');
INSERT INTO `workflow_log_message` VALUES ('fc655b032a1f475e85f8f978b1c998fa', '我是市场主管1', '6', '2019-10-14 23:07:55', '订单号: 0001,业务类型: 公积金代缴,当前节点: 公积金代缴,原处理人: 我是售后_1,变更处理人: 我是售后2', 'b3dd4297235248538339a475164f5dfa', '0', null, null, null, null);
INSERT INTO `workflow_log_message` VALUES ('ff605d17289a4014b2295f4d967fad57', '我是主管1', '8', '2019-09-14 11:30:48', '请快速解决！', '63a69f219efc4ccb96e6b9013fb24d0e', '0', null, null, null, null);

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
