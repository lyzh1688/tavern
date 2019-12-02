package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_log_message")
public class WorkFlowLogMessage extends Model<WorkFlowLogMessage> {
    @TableId
    private String logId;

    private String operator;

    private String operatorName;

    private String createTime;

    private String message;

    private String eventId;

    private String hasAttachment;

    private String attachmentsPath;
    private String hasRefund;
    private BigDecimal refundFee;

    private String nodeId;

    private String refundWay;

}