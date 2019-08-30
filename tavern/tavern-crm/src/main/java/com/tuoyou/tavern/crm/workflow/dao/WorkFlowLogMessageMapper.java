package com.tuoyou.tavern.crm.workflow.dao;

import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
import java.util.List;

public interface WorkFlowLogMessageMapper {
    int deleteByPrimaryKey(String logId);

    int insert(WorkFlowLogMessage record);

    WorkFlowLogMessage selectByPrimaryKey(String logId);

    List<WorkFlowLogMessage> selectAll();

    int updateByPrimaryKey(WorkFlowLogMessage record);
}