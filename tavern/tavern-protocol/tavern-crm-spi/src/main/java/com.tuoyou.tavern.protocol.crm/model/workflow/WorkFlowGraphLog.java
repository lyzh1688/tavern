package com.tuoyou.tavern.protocol.crm.model.workflow;

import lombok.Data;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/14 <br>
 */
@Data
public class WorkFlowGraphLog {
   private String name;
   private String nodeId;
   private String operator;
   private String sourceNode;
   private String targetNode;
   private String rootNode;

}
