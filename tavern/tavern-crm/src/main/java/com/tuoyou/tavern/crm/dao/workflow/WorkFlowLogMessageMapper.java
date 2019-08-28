package com.tuoyou.tavern.crm.dao.workflow;

import com.tuoyou.tavern.crm.entity.WorkFlowLogMessage;
import java.util.List;

public interface WorkFlowLogMessageMapper {
    int deleteByPrimaryKey(String logId);

    int insert(WorkFlowLogMessage record);

    WorkFlowLogMessage selectByPrimaryKey(String logId);

    List<WorkFlowLogMessage> selectAll();

    int updateByPrimaryKey(WorkFlowLogMessage record);
}