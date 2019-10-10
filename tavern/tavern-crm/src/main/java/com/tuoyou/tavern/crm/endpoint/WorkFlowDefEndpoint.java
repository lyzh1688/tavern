package com.tuoyou.tavern.crm.endpoint;

import com.tuoyou.tavern.crm.workflow.service.WorkFlowDefNodeService;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowNodeQueryDTO;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowDefNodeResponse;
import com.tuoyou.tavern.protocol.hrm.spi.HrmUserDictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/04 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/def")
public class WorkFlowDefEndpoint {

    private WorkFlowDefNodeService workFlowDefNodeService;
    private HrmUserDictService hrmUserDictService;

    /**
     * 查询下一节点
     */
    @GetMapping("/nextNode")
    public WorkFlowDefNodeResponse getWorkFlowNode(WorkFlowNodeQueryDTO workFlowNodeQueryDTO) {
        return new WorkFlowDefNodeResponse(this.workFlowDefNodeService.getWorkFlowDefNode(workFlowNodeQueryDTO));
    }


    /**
     * 对接人员
     */
    @GetMapping("/operator")
    public TavernDictResponse getWorkFlowPointOperator(@RequestParam(name = "role") String role) {
        return this.hrmUserDictService.queryStaffByRole(role);
    }



}
