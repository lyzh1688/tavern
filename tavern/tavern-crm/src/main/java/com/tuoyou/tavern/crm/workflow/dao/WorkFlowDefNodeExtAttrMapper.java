package com.tuoyou.tavern.crm.workflow.dao;

import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNodeExtAttr;
import java.util.List;

public interface WorkFlowDefNodeExtAttrMapper {
    int deleteByPrimaryKey(String nodeId);

    int insert(WorkFlowDefNodeExtAttr record);

    WorkFlowDefNodeExtAttr selectByPrimaryKey(String nodeId);

    List<WorkFlowDefNodeExtAttr> selectAll();

    int updateByPrimaryKey(WorkFlowDefNodeExtAttr record);
}