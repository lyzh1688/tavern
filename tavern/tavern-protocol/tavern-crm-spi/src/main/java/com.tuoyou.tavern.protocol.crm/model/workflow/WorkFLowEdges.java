package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/14 <br>
 */
@Data
public class WorkFLowEdges {

    public WorkFLowEdges(String source, String target) {
        this.source = source;
        this.target = target;
    }

    private String source;
    private String target;

}
