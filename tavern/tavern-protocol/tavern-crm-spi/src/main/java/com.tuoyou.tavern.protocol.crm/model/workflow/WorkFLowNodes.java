package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/14 <br>
 */
@Data
public class WorkFLowNodes {
    private String id;
    private String label;
    private String operatorId;

    public WorkFLowNodes(String id, String label, String operatorId) {
        this.id = id;
        this.label = label;
        this.operatorId = operatorId;
    }
}
