package com.tuoyou.tavern.crm.dao.workflow;

import com.tuoyou.tavern.crm.entity.WorkFlowEventHistory;
import java.util.List;

public interface WorkFlowEventHistoryMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(WorkFlowEventHistory record);

    WorkFlowEventHistory selectByPrimaryKey(String eventId);

    List<WorkFlowEventHistory> selectAll();

    int updateByPrimaryKey(WorkFlowEventHistory record);
}