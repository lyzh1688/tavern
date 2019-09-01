package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNode;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowDefNodeService extends IService<WorkFlowDefNode> {

    TavernDictResponse getWorkFlowOwnerInfo(String business);

    WorkFlowDefNode getWorkFlowOwnerInfoByBusiness(String business);

}
