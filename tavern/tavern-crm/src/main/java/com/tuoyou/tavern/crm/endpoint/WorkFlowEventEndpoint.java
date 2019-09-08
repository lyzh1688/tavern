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
import java.util.List;

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
            @RequestParam("message") String message,
            @RequestParam("eventId") String eventId,
            @RequestParam("filex") MultipartFile files1,
            @RequestParam("filey") MultipartFile files2,
            @RequestParam("filez") MultipartFile files3,
            @RequestParam("files") MultipartFile[] files4,
            @RequestParam(name = "refundFee",required = false) BigDecimal refundFee) throws Exception {
//        if (StringUtils.isNoneEmpty(message) && files != null && !files.isEmpty()) {
//            WorkFlowLogMessageDTO workFlowLogMessageDTO = new WorkFlowLogMessageDTO();
//            workFlowLogMessageDTO.setOperator(operator);
//            workFlowLogMessageDTO.setOperatorName(operatorName);
//            workFlowLogMessageDTO.setMessage(message);
//            workFlowLogMessageDTO.setEventId(eventId);
//            workFlowLogMessageDTO.setFiles(files);
//            workFlowLogMessageDTO.setRefundFee(refundFee);
//            this.workFlowLogMessageService.saveWorkFlowLog(workFlowLogMessageDTO);
//        }
        return new TavernResponse();
    }


    /**
     * 更新流程
     */
    @PostMapping("/next")
    public TavernResponse startNextWorkFlow(WorkFlowNextNodeDTO workFlowNextNodeDTO) throws Exception {
        this.workFlowEventService.startNextWorkFlow(workFlowNextNodeDTO);
        return new TavernResponse();
    }

}
