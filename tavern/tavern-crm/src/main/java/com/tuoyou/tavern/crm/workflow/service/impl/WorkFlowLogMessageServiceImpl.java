package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventMapper;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowLogMessageMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class WorkFlowLogMessageServiceImpl extends ServiceImpl<WorkFlowLogMessageMapper, WorkFlowLogMessage> implements WorkFlowLogMessageService {
}
