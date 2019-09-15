package com.tuoyou.tavern.crm.workflow.dto;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/15 <br>
 */
@Data
public class WorkFlowRefundDTO {
    private String eventId;
    private String operator;
    private String operatorName;
    private String message;
    private String curNodeId;
    private String agreeRefund;
    private String handlerId;
    private String handlerName;
}
