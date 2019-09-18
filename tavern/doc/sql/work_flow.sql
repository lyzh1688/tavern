/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : work_flow

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-09-19 02:01:20
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
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_cn_end', 'g_sbzc_node_cn', 'g_sbzc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sczg_cn', 'g_sbzc_node_sczg', 'g_sbzc_node_cn', '退款');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sh_end', 'g_sbzc_node_sh', 'g_sbzc_node_end', '结束');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sh_sczg', 'g_sbzc_node_sh', 'g_sbzc_node_sczg', '退款审批');
INSERT INTO `workflow_def_edge` VALUES ('g_sbzc', 'g_sbzc_edge_sq_sh', 'g_sbzc_node_sq', 'g_sbzc_node_sh', '商标注册');

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
INSERT INTO `workflow_def_graph` VALUES ('g_sbzc', '商标注册', 'g_sbzc_node_sq', 'g_sbzc_node_end');

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
INSERT INTO `workflow_def_graph_business_rel` VALUES ('g_dljz', 'biz_3');

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
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_cn', '退款', 'R_CN');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_end', '结束', '');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_sczg', '退款审批', 'R_SCZG');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_sh', '商标注册', 'R_SH');
INSERT INTO `workflow_def_node` VALUES ('g_sbzc', 'g_sbzc_node_sq', '客户登记', 'R_SQ');

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
