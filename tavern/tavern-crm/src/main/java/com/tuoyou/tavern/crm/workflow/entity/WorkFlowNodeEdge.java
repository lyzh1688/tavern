package com.tuoyou.tavern.crm.workflow.entity;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/10/13 <br>
 */
@Data
public class WorkFlowNodeEdge {
    private String preNodeId;
    private String curNodeId;
    private String role;
}
