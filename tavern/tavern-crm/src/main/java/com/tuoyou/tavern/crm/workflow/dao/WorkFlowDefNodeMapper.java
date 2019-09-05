package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowDefNode;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowDefNodeVO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowNodeQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkFlowDefNodeMapper extends BaseMapper<WorkFlowDefNode> {

    WorkFlowDefNode selectWorkFlowDefNodeByBusiness(@Param("business") String business);

    List<WorkFlowDefNodeVO> selectWorkFlowDefNode(@Param("query") WorkFlowNodeQueryDTO workFlowNodeQueryDTO);


}