/*
Navicat MySQL Data Transfer

Source Server         : 119.3.19.171
Source Server Version : 50560
Source Host           : 119.3.19.171:3306
Source Database       : work_flow

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-27 15:59:07
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
  `tag` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`edge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='workflow_def_edge';

-- ----------------------------
-- Records of workflow_def_edge
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_def_graph
-- ----------------------------
DROP TABLE IF EXISTS `workflow_def_graph`;
CREATE TABLE `workflow_def_graph` (
  `graph_id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`graph_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流定义';

-- ----------------------------
-- Records of workflow_def_graph
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_def_node
-- ----------------------------
DROP TABLE IF EXISTS `workflow_def_node`;
CREATE TABLE `workflow_def_node` (
  `graph_id` varchar(45) NOT NULL,
  `node_id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`graph_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流节点定义';

-- ----------------------------
-- Records of workflow_def_node
-- ----------------------------

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
  `begin_date` date DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件';

-- ----------------------------
-- Records of workflow_event
-- ----------------------------

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
  `graph_id` varchar(45) DEFAULT NULL,
  `node_id` varchar(45) DEFAULT NULL,
  `begin_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `operator` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流事件历史';

-- ----------------------------
-- Records of workflow_event_history
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_log_attatchment
-- ----------------------------
DROP TABLE IF EXISTS `workflow_log_attatchment`;
CREATE TABLE `workflow_log_attatchment` (
  `file_id` varchar(45) NOT NULL,
  `log_id` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `file_path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程日志附件表';

-- ----------------------------
-- Records of workflow_log_attatchment
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_log_message
-- ----------------------------
DROP TABLE IF EXISTS `workflow_log_message`;
CREATE TABLE `workflow_log_message` (
  `log_id` varchar(45) NOT NULL,
  `operator` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  `event_id` varchar(45) DEFAULT NULL,
  `has_attatchment` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程日志表';

-- ----------------------------
-- Records of workflow_log_message
-- ----------------------------

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
