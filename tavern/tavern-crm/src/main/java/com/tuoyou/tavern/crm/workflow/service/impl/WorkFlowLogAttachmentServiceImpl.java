package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowLogAttachmentMapper;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowLogMessageMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogAttachment;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogAttachmentService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class WorkFlowLogAttachmentServiceImpl extends ServiceImpl<WorkFlowLogAttachmentMapper, WorkFlowLogAttachment> implements WorkFlowLogAttachmentService {
}
