package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/14 <br>
 */
@Data
public class WorkFlowGraphLogVO {

    public WorkFlowGraphLogVO(List<WorkFLowNodes> nodes, List<WorkFLowEdges> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    private List<WorkFLowNodes> nodes;
    private List<WorkFLowEdges> edges;

}
