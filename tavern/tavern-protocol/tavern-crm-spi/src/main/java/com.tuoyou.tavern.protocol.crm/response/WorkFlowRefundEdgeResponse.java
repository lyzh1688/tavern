package com.tuoyou.tavern.protocol.crm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowRefundVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/10/13 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class WorkFlowRefundEdgeResponse extends TavernResponse {
    private List<WorkFlowRefundVO> data;
}