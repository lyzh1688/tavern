package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
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
    private String nodeName;
}
