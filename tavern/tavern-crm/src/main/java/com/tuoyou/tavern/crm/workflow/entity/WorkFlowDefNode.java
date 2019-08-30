package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_def_node")
public class WorkFlowDefNode extends Model<WorkFlowDefNode> {
    @TableId
    private String nodeId;

    private String graphId;

    private String name;

    private String role;

}