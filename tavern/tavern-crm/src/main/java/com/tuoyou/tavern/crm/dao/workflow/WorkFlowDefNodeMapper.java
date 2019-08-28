package com.tuoyou.tavern.crm.dao.workflow;

import com.tuoyou.tavern.crm.entity.WorkFlowDefNode;
import java.util.List;

public interface WorkFlowDefNodeMapper {
    int deleteByPrimaryKey(String nodeId);

    int insert(WorkFlowDefNode record);

    WorkFlowDefNode selectByPrimaryKey(String nodeId);

    List<WorkFlowDefNode> selectAll();

    int updateByPrimaryKey(WorkFlowDefNode record);
}