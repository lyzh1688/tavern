package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventDependency;
import org.apache.ibatis.annotations.Param;

public interface WorkFlowEventDependencyMapper extends BaseMapper<WorkFlowEventDependency> {

    WorkFlowEventDependency selectWorkFlowEventDependency(@Param("eventId") String preEventId);

}