package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogAttachment;

import java.util.Collection;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowLogAttachmentService extends IService<WorkFlowLogAttachment> {

    boolean saveWorkFlowLogAttachmentBatch(Collection<WorkFlowLogAttachment> entityList);
}
