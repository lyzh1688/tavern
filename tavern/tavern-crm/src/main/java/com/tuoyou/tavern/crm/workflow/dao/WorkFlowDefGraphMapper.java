package com.tuoyou.tavern.crm.workflow.dao;

import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefGraph;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkFlowDefGraphMapper {
    int deleteByPrimaryKey(@Param("graphId") String graphId, @Param("rootNode") String rootNode);

    int insert(WorkFlowDefGraph record);

    WorkFlowDefGraph selectByPrimaryKey(@Param("graphId") String graphId, @Param("rootNode") String rootNode);

    List<WorkFlowDefGraph> selectAll();

    int updateByPrimaryKey(WorkFlowDefGraph record);
}