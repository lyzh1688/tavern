package com.tuoyou.tavern.crm.workflow.dao;

import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventDependency;
import java.util.List;

public interface WorkFlowEventDependencyMapper {
    int deleteByPrimaryKey(String preEvent);

    int insert(WorkFlowEventDependency record);

    WorkFlowEventDependency selectByPrimaryKey(String preEvent);

    List<WorkFlowEventDependency> selectAll();

    int updateByPrimaryKey(WorkFlowEventDependency record);
}