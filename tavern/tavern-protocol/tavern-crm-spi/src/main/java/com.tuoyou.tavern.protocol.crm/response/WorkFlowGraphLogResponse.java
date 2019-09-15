package com.tuoyou.tavern.protocol.crm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowGraphLogVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/14 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class WorkFlowGraphLogResponse extends TavernResponse {
    private WorkFlowGraphLogVO data;
}
