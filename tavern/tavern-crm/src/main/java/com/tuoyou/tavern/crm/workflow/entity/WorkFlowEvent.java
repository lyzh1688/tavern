package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_event")
public class WorkFlowEvent extends Model<WorkFlowEvent> {
    @TableId
    private String eventId;

    private String graphId;

    private String curNodeId;

    private String eventOwner;

    private String curOperator;

    private String curOperatorName;

    private LocalDateTime beginDate;

}