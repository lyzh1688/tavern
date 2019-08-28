package com.tuoyou.tavern.crm.entity;

import com.google.common.collect.Lists;
import com.tuoyou.tavern.protocol.common.model.Dict;
import lombok.Data;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/29 <br>
 */
@Data
public class GraphNodeRoleInfoVO {
    private String graphId;
    private String graphName;
    private String nodeId;
    private List<Dict> roleUserList = Lists.newArrayList();
}
