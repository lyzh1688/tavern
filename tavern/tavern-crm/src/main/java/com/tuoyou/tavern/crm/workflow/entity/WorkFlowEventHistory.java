package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_event_history")
public class WorkFlowEventHistory extends Model<WorkFlowEventHistory> {
    private String eventId;

    private String graphId;

    private String nodeId;

    private String nextNodeId;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private String operator;

}