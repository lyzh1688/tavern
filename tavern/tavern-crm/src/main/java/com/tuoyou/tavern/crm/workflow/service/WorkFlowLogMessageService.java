package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
import com.tuoyou.tavern.protocol.crm.dto.workflow.WorkFlowLogQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowLogVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowLogMessageService extends IService<WorkFlowLogMessage> {

    IPage<WorkFlowLogVO> getWorkFlowLogMessagePage(Page page, WorkFlowLogQueryDTO workFlowLogQueryDTO);

    void saveWorkFlowLog(WorkFlowLogMessageDTO workFlowLogMessageDTO) throws Exception;



}
