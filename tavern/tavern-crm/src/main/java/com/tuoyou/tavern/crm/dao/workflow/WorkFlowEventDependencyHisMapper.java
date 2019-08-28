package com.tuoyou.tavern.crm.dao.workflow;

import com.tuoyou.tavern.crm.entity.WorkFlowEventDependencyHis;
import java.util.List;

public interface WorkFlowEventDependencyHisMapper {
    int deleteByPrimaryKey(String preEvent);

    int insert(WorkFlowEventDependencyHis record);

    WorkFlowEventDependencyHis selectByPrimaryKey(String preEvent);

    List<WorkFlowEventDependencyHis> selectAll();

    int updateByPrimaryKey(WorkFlowEventDependencyHis record);
}