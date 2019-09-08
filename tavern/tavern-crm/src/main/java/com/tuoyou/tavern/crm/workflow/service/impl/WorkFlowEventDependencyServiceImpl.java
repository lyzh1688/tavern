package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventDependencyMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventDependency;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventDependencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class WorkFlowEventDependencyServiceImpl extends ServiceImpl<WorkFlowEventDependencyMapper, WorkFlowEventDependency> implements WorkFlowEventDependencyService {

    @TargetDataSource(name = "workflow")
    @Override
    public boolean save(WorkFlowEventDependency entity) {
        return super.save(entity);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public WorkFlowEventDependency getById(Serializable id) {
        return super.getById(id);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public WorkFlowEventDependency getWorkFlowEventDependency(String eventId) {
        return this.baseMapper.selectWorkFlowEventDependency(eventId);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
