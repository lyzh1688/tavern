package com.tuoyou.tavern.protocol.crm.dto.workflow;

import lombok.Data;

@Data
public class WorkFlowLogQueryDTO {
    private String eventId;
    private String operator;
    private String curNodeId;
}