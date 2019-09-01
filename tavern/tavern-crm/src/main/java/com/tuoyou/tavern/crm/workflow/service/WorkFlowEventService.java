package com.tuoyou.tavern.crm.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import org.apache.ibatis.annotations.Param;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface WorkFlowEventService extends IService<WorkFlowEvent> {

    void startWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    int saveWorkFlowEvent(WorkFlowEvent workFlowEvent, String businessId);


}
