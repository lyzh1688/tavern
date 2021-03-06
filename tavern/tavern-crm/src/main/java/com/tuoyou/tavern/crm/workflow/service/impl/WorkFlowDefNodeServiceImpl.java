package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowDefNodeMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNode;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowDefNodeService;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowDefNodeVO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowNodeQueryDTO;
import com.tuoyou.tavern.protocol.hrm.spi.HrmUserDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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

    @TargetDataSource(name = "workflow")
    @Override
    public List<WorkFlowDefNodeVO> getWorkFlowDefNode(WorkFlowNodeQueryDTO workFlowNodeQueryDTO) {
        return this.baseMapper.selectWorkFlowDefNode(workFlowNodeQueryDTO);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public List<WorkFlowDefNodeVO> getWorkFlowRootNextNode(String businessId) {
        return this.baseMapper.selectWorkFlowRootNextNode(businessId);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public Collection<WorkFlowDefNode> getList(List<String> nodeList) {
        return this.workFlowDefNodeService.listByIds(nodeList);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public WorkFlowDefNode getByNodeId(String nodeId) {
        return this.baseMapper.selectById(nodeId);
    }
}
