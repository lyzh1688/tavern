package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_def_graph")
public class WorkFlowDefGraph extends Model<WorkFlowDefGraph> {
    private String graphId;

    private String rootNode;

    private String name;

    private String endNode;

}