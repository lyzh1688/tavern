package com.tuoyou.tavern.crm.workflow.dao;

import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefEdge;
import java.util.List;

public interface WorkFlowDefEdgeMapper {
    int deleteByPrimaryKey(String edgeId);

    int insert(WorkFlowDefEdge record);

    WorkFlowDefEdge selectByPrimaryKey(String edgeId);

    List<WorkFlowDefEdge> selectAll();

    int updateByPrimaryKey(WorkFlowDefEdge record);
}