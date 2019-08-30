package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNode;
import org.apache.ibatis.annotations.Param;

public interface WorkFlowDefNodeMapper extends BaseMapper<WorkFlowDefNode> {

    WorkFlowDefNode selectWorkFlowDefNodeByBusiness(@Param("business") String business);


}