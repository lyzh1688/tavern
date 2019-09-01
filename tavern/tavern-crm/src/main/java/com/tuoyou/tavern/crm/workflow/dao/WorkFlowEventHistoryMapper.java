package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventHistory;
import org.apache.ibatis.annotations.Param;

public interface WorkFlowEventHistoryMapper extends BaseMapper<WorkFlowEventHistory> {
    int insertWorkFlowEventFirstNodeHistory(@Param("event") WorkFlowEventHistory workFlowEventHistory, @Param("businessId") String businessId);

    int insertWorkFlowEventHistory(@Param("event") WorkFlowEventHistory workFlowEventHistory, @Param("businessId") String businessId);
}