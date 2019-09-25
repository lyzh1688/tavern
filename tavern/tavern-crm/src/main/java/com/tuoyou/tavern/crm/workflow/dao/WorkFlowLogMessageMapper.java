package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
import com.tuoyou.tavern.protocol.crm.dto.workflow.WorkFlowLogQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowGraphLog;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkFlowLogMessageMapper extends BaseMapper<WorkFlowLogMessage> {
    IPage<WorkFlowLogVO> selectWorkFlowLogMessagePage(Page page, @Param("query") WorkFlowLogQueryDTO workFlowLogQueryDTO);

    List<WorkFlowGraphLog> selectWorkFlowGraphLog(@Param("eventId") String eventId);

    int insertRootWorkLog(@Param("log") WorkFlowLogMessage workFlowLogMessage, @Param("businessId") String businessId);
}