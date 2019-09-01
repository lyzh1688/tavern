package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import org.apache.ibatis.annotations.Param;

public interface WorkFlowEventMapper extends BaseMapper<WorkFlowEvent> {
    int insertWorkFlowEvent(@Param("event") WorkFlowEvent workFlowEvent, @Param("businessId") String businessId);
}