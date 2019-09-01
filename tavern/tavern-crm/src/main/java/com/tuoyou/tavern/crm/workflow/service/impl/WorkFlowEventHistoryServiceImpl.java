package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventHistoryMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventHistory;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class WorkFlowEventHistoryServiceImpl extends ServiceImpl<WorkFlowEventHistoryMapper, WorkFlowEventHistory> implements WorkFlowEventHistoryService {

    @TargetDataSource(name = "workflow")
    @Override
    public int saveWorkFlowEventFirstNodeHistory(WorkFlowEventHistory workFlowEventHistory, String businessId) {
        return this.baseMapper.insertWorkFlowEventFirstNodeHistory(workFlowEventHistory, businessId);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public int saveWorkFlowEventHistory(WorkFlowEventHistory workFlowEventHistory, String businessId) {
        return this.baseMapper.insertWorkFlowEventHistory(workFlowEventHistory, businessId);
    }

}
