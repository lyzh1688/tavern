package com.tuoyou.tavern.crm.workflow.dao;

import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventHistory;
import java.util.List;

public interface WorkFlowEventHistoryMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(WorkFlowEventHistory record);

    WorkFlowEventHistory selectByPrimaryKey(String eventId);

    List<WorkFlowEventHistory> selectAll();

    int updateByPrimaryKey(WorkFlowEventHistory record);
}