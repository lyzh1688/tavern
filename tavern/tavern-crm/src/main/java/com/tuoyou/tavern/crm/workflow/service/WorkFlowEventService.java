package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowNextNodeDTO;
import com.tuoyou.tavern.protocol.crm.dto.workflow.MyToDoListDTO;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.model.workflow.MyTodoListVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowEventService extends IService<WorkFlowEvent> {

    void startWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    int saveWorkFlowEvent(WorkFlowEvent workFlowEvent, String businessId);

    IPage<MyTodoListVO> getMyPendingWork(Page page, MyToDoListDTO myToDoListDTO);

    void startNextWorkFlow(WorkFlowNextNodeDTO workFlowNextNodeDTO);


}
