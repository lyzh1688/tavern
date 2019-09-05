package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogMessageService;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.workflow.MyToDoListDTO;
import com.tuoyou.tavern.protocol.crm.dto.workflow.WorkFlowLogQueryDTO;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowLogPageResponse;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowTodoListResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/04 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/workflow")
public class WorkFlowEventEndpoint {

    private WorkFlowEventService workFlowEventService;
    private WorkFlowLogMessageService workFlowLogMessageService;

    /**
     * 我的代办
     */
    @GetMapping("/todo/page")
    public WorkFlowTodoListResponse getWorkFlowTodoList(Page page, MyToDoListDTO myToDoListDTO) {
        return new WorkFlowTodoListResponse(this.workFlowEventService.getMyPendingWork(page, myToDoListDTO));
    }

    /**
     * 历史备注查询
     */
    @GetMapping("/log/page")
    public WorkFlowLogPageResponse getWorkFlowLogPage(Page page, WorkFlowLogQueryDTO workFlowLogQueryDTO) {
        return new WorkFlowLogPageResponse(this.workFlowLogMessageService.getWorkFlowLogMessagePage(page, workFlowLogQueryDTO));
    }

    /**
     * 备注添加
     */
    @PostMapping
    public TavernResponse saveWorkFlowLog(@RequestBody WorkFlowLogMessageDTO workFlowLogMessageDTO) throws Exception {
        if(StringUtils.isNoneEmpty(workFlowLogMessageDTO.getMessage())&&!workFlowLogMessageDTO.getFiles().isEmpty()){
            this.workFlowLogMessageService.saveWorkFlowLog(workFlowLogMessageDTO);
        }
        return new TavernResponse();
    }


    /**
     * 更新流程
     */


}
