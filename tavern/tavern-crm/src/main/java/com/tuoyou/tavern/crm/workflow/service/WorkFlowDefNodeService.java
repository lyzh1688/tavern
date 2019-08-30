package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNode;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/8/30 <br>
 */
public interface WorkFlowDefNodeService extends IService<WorkFlowDefNode> {

    TavernDictResponse getWorkFlowOwnerInfo(String business);

    WorkFlowDefNode getWorkFlowOwnerInfoByBusiness(String business);

}
