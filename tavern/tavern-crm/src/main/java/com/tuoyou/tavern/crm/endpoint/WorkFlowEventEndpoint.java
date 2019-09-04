package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.workflow.dto.MyToDoListDTO;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventService;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowTodoListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/04 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/workflow")
public class WorkFlowEventEndpoint {

    private WorkFlowEventService workFlowEventService;

    @GetMapping("/todo/page")
    public WorkFlowTodoListResponse getWorkFlowTodoList(Page page, MyToDoListDTO myToDoListDTO) {
        return new WorkFlowTodoListResponse(this.workFlowEventService.getMyPendingWork(page, myToDoListDTO));
    }
}
