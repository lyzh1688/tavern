package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/9/5 <br>
 */
@Data
public class WorkFlowLogVO {
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
}
