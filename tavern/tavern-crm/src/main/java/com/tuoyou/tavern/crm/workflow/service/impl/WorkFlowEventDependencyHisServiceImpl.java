package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventDependencyHisMapper;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventDependencyMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventDependency;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventDependencyHis;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventDependencyHisService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventDependencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class WorkFlowEventDependencyHisServiceImpl extends ServiceImpl<WorkFlowEventDependencyHisMapper, WorkFlowEventDependencyHis> implements WorkFlowEventDependencyHisService {

    @TargetDataSource(name = "workflow")
    @Override
    public boolean save(WorkFlowEventDependencyHis entity) {
        return super.save(entity);
    }
}
