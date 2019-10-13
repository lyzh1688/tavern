package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/10/13 <br>
 */
@Data
public class WorkFlowRefundVO {
    private String id;
    private String name;
    private String preNodeId;
    private String curNodeId;

}
