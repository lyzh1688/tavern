package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
import com.tuoyou.tavern.protocol.crm.dto.workflow.WorkFlowLogQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowLogVO;
import org.apache.ibatis.annotations.Param;

public interface WorkFlowLogMessageMapper extends BaseMapper<WorkFlowLogMessage> {
    IPage<WorkFlowLogVO> selectWorkFlowLogMessagePage(Page page, @Param("query") WorkFlowLogQueryDTO workFlowLogQueryDTO);
}