package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_def_graph_business_rel")
public class WorkFlowDefGraphBusinessRel extends Model<WorkFlowDefNode> {
    @TableId
    private String graphId;
    @TableId
    private String businessId;

}