package com.tuoyou.tavern.crm.workflow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.workflow.dto.MyToDoListDTO;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.protocol.crm.model.MyTodoListVO;
import org.apache.ibatis.annotations.Param;

public interface WorkFlowEventMapper extends BaseMapper<WorkFlowEvent> {
    int insertWorkFlowEvent(@Param("event") WorkFlowEvent workFlowEvent, @Param("businessId") String businessId);

    IPage<MyTodoListVO> selectMyPendingWork(Page page, @Param("query") MyToDoListDTO myToDoListDTO);
}