package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_def_node_ext_attr")
public class WorkFlowDefNodeExtAttr extends Model<WorkFlowDefNodeExtAttr> {
    @TableId
    private String nodeId;

    private String restDays;

    private String nextNodeTriggerHandler;

}