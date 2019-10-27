package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowDelayNotesDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowNextNodeDTO;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.crm.dto.workflow.MyToDoListDTO;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.model.workflow.MyTodoListVO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowRefundVO;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowEventService extends IService<WorkFlowEvent> {

    void startWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    void updateWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    void removeWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    int saveWorkFlowEvent(WorkFlowEvent workFlowEvent, String businessId);

    IPage<MyTodoListVO> getMyPendingWork(Page page, MyToDoListDTO myToDoListDTO);

    IPage<MyTodoListVO> getAllWorkEvent(Page page, MyToDoListDTO myToDoListDTO);

    void startNextWorkFlow(WorkFlowNextNodeDTO workFlowNextNodeDTO) throws Exception;

    void delayWorkEvent(WorkFlowDelayNotesDTO workFlowDelayNotesDTO);

    void reChooseHandler(List<WorkFlowNextNodeDTO> workFlowNextNodeDTOList) throws Exception;

    TavernDictResponse getReChooseHandler(String curNodeId, String curNodeName);

    List<WorkFlowRefundVO> getWorkFlowRefundOperator(String eventId, String nodeId, String direction);


}
