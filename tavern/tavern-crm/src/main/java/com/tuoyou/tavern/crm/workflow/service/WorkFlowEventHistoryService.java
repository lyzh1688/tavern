package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventHistory;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowEventHistoryService extends IService<WorkFlowEventHistory> {
    int saveWorkFlowEventFirstNodeHistory(WorkFlowEventHistory workFlowEventHistory, String businessId);
    int saveWorkFlowEventHistory(WorkFlowEventHistory workFlowEventHistory, String businessId);
}
