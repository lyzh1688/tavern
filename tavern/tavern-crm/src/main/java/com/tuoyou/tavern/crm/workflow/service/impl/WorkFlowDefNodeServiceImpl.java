package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowDefNodeMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNode;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowDefNodeService;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.hrm.spi.HrmUserDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
public class WorkFlowDefNodeServiceImpl extends ServiceImpl<WorkFlowDefNodeMapper, WorkFlowDefNode> implements WorkFlowDefNodeService {

    @Autowired
    private WorkFlowDefNodeService workFlowDefNodeService;
    @Autowired
    private HrmUserDictService hrmUserDictService;

    @Override
    public TavernDictResponse getWorkFlowOwnerInfo(String business) {

        WorkFlowDefNode workFlowDefNode = this.workFlowDefNodeService.getWorkFlowOwnerInfoByBusiness(business);
        if(Objects.isNull(workFlowDefNode)){
            return new TavernDictResponse(Lists.newArrayList());
        }
        return this.hrmUserDictService.queryStaffByRole(workFlowDefNode.getRole());

    }

    @TargetDataSource(name = "workflow")
    @Override
    public WorkFlowDefNode getWorkFlowOwnerInfoByBusiness(String business) {
        return this.baseMapper.selectWorkFlowDefNodeByBusiness(business);
    }
}
