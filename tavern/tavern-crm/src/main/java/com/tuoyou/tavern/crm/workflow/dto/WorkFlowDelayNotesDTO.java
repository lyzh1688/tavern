package com.tuoyou.tavern.crm.workflow.dto;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/15 <br>
 */
@Data
public class WorkFlowDelayNotesDTO {
    private String orderId;
    private String businessId;
    private String companyId;
    private String eventId;
    private String operator;
    private String operatorName;
    private String message;
    private int delayDays;
}
