package com.tuoyou.tavern.crm.workflow.dao;

import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import java.util.List;

public interface WorkFlowEventMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(WorkFlowEvent record);

    WorkFlowEvent selectByPrimaryKey(String eventId);

    List<WorkFlowEvent> selectAll();

    int updateByPrimaryKey(WorkFlowEvent record);
}