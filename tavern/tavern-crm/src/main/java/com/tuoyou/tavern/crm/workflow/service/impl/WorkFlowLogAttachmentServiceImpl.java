package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowLogAttachmentMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogAttachment;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogAttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class WorkFlowLogAttachmentServiceImpl extends ServiceImpl<WorkFlowLogAttachmentMapper, WorkFlowLogAttachment> implements WorkFlowLogAttachmentService {


    @TargetDataSource(name = "workflow")
    @Override
    public boolean saveWorkFlowLogAttachmentBatch(Collection<WorkFlowLogAttachment> entityList) {
        return this.saveBatch(entityList);
    }
}
