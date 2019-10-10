package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNode;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowDefNodeVO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowNodeQueryDTO;

import java.util.Collection;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowDefNodeService extends IService<WorkFlowDefNode> {

    TavernDictResponse getWorkFlowOwnerInfo(String business);

    WorkFlowDefNode getWorkFlowOwnerInfoByBusiness(String business);

    List<WorkFlowDefNodeVO> getWorkFlowDefNode(WorkFlowNodeQueryDTO workFlowNodeQueryDTO);

    Collection<WorkFlowDefNode> getList(List<String> nodeList);

    WorkFlowDefNode getByNodeId(String nodeId);

}
