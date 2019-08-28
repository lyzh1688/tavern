package com.tuoyou.tavern.crm.dao.workflow;

import com.tuoyou.tavern.crm.entity.WorkFlowDefNodeExtAttr;
import java.util.List;

public interface WorkFlowDefNodeExtAttrMapper {
    int deleteByPrimaryKey(String nodeId);

    int insert(WorkFlowDefNodeExtAttr record);

    WorkFlowDefNodeExtAttr selectByPrimaryKey(String nodeId);

    List<WorkFlowDefNodeExtAttr> selectAll();

    int updateByPrimaryKey(WorkFlowDefNodeExtAttr record);
}