package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_log_message")
public class WorkFlowLogMessage extends Model<WorkFlowLogMessage> {
    @TableId
    private String logId;

    private String operator;

    private String operatorId;

    private String createTime;

    private String message;

    private String eventId;

    private String hasAttachment;

    private String attachmentsPath;

}