package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_event_dependency")
public class WorkFlowEventDependency extends Model<WorkFlowEventDependency> {
    private String preEvent;

    @TableId
    private String nextEvent;

    private LocalDateTime createDate;

}