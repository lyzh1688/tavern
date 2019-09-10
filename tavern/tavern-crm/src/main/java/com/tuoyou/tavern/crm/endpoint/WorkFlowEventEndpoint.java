package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowNextNodeDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/04 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/event")
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
    @PostMapping("/log/save")
    public TavernResponse saveWorkFlowLog(
            @RequestParam("operator") String operator,
            @RequestParam("operatorName") String operatorName,
            @RequestParam(name = "message", required = false) String message,
            @RequestParam("eventId") String eventId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam(name = "refundFee", required = false) BigDecimal refundFee) throws Exception {
        WorkFlowLogMessageDTO workFlowLogMessageDTO = new WorkFlowLogMessageDTO();
        workFlowLogMessageDTO.setOperator(operator);
        workFlowLogMessageDTO.setOperatorName(operatorName);
        workFlowLogMessageDTO.setMessage(message);
        workFlowLogMessageDTO.setEventId(eventId);
        workFlowLogMessageDTO.setFiles(Arrays.asList(files));
        workFlowLogMessageDTO.setRefundFee(refundFee);
        this.workFlowLogMessageService.saveWorkFlowLog(workFlowLogMessageDTO);
        return new TavernResponse();
    }


    /**
     * 更新流程
     */
    @PostMapping("/next")
    public TavernResponse startNextWorkFlow(
            @RequestParam("eventId") String eventId,
            @RequestParam("curNodeId") String curNodeId,
            @RequestParam("curOperator") String curOperator,
            @RequestParam("curOperatorName") String curOperatorName,
            @RequestParam(name = "message", required = false) String message,
            @RequestParam(name = "files",required = false) MultipartFile[] files,
            @RequestParam(name = "refundFee", required = false) BigDecimal refundFee) throws Exception {
        WorkFlowNextNodeDTO workFlowNextNodeDTO = new WorkFlowNextNodeDTO(eventId,curNodeId,curOperator,curOperatorName,message,Arrays.asList(files),refundFee);
        this.workFlowEventService.startNextWorkFlow(workFlowNextNodeDTO);
        return new TavernResponse();
    }

}
