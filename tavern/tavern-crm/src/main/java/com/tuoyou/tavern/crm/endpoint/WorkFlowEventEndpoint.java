package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowDelayNotesDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowNextNodeDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowRefundDTO;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogMessageService;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.workflow.MyToDoListDTO;
import com.tuoyou.tavern.protocol.crm.dto.workflow.WorkFlowLogQueryDTO;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowGraphLogResponse;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowLogPageResponse;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowRefundEdgeResponse;
import com.tuoyou.tavern.protocol.crm.response.WorkFlowTodoListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * 所有代办
     */
    @GetMapping("/todo/all")
    public WorkFlowTodoListResponse getAllWorkFlowList(Page page, MyToDoListDTO myToDoListDTO) {
        return new WorkFlowTodoListResponse(this.workFlowEventService.getAllWorkEvent(page, myToDoListDTO));
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
            @RequestParam(name = "curNodeId") String curNodeId,
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
        workFlowLogMessageDTO.setCurNodeId(curNodeId);
        this.workFlowLogMessageService.saveWorkFlowLog(workFlowLogMessageDTO);
        return new TavernResponse();
    }


    /**
     * 更新流程
     */
    @PostMapping("/next")
    public TavernResponse startNextWorkFlow(
            @RequestParam("eventId") String eventId,
            @RequestParam("preNodeId") String preNodeId,
            @RequestParam("curNodeId") String curNodeId,
            @RequestParam("curOperator") String curOperator,
            @RequestParam("curOperatorName") String curOperatorName,
            @RequestParam("operator") String operator,
            @RequestParam("operatorName") String operatorName,
            @RequestParam(name = "message", required = false) String message,
            @RequestParam(name = "files", required = false) MultipartFile[] files,
            @RequestParam(name = "refundFee", required = false) BigDecimal refundFee,
            @RequestParam(name = "thirdPartyFlag", required = false) String thirdPartyFlag,
            @RequestParam(name = "thirdPartyId", required = false) String thirdPartyId,
            @RequestParam(name = "thirdPartyInfo", required = false) String thirdPartyInfo,
            @RequestParam(name = "thirdPartyFee", required = false) String thirdPartyFee
    ) throws Exception {
        WorkFlowNextNodeDTO workFlowNextNodeDTO = new WorkFlowNextNodeDTO(eventId, preNodeId, curNodeId, curOperator, curOperatorName, operator, operatorName, message, Arrays.asList(files), refundFee,thirdPartyFlag,thirdPartyId,thirdPartyInfo,thirdPartyFee);
        this.workFlowEventService.startNextWorkFlow(workFlowNextNodeDTO);
        return new TavernResponse();
    }

    /**
     * 流程日志
     */
    @GetMapping("/log/graph")
    public WorkFlowGraphLogResponse getWorkFlowGraph(@RequestParam("eventId") String eventId) {
        WorkFlowGraphLogResponse response = new WorkFlowGraphLogResponse(this.workFlowLogMessageService.getWorkFlowGraphLog(eventId));
        return response;
    }

    /**
     * 延期批示
     */
    @PostMapping("/delay")
    public TavernResponse delayWorkEvent(@RequestBody WorkFlowDelayNotesDTO workFlowDelayNotesDTO) {
        this.workFlowEventService.delayWorkEvent(workFlowDelayNotesDTO);
        return new TavernResponse();
    }

    /**
     * 退款处理
     */
    @PostMapping("/refund")
    public TavernResponse refundWorkEvent(@RequestBody WorkFlowRefundDTO workFlowRefundDTO) throws Exception {
        WorkFlowNextNodeDTO workFlowNextNodeDTO = new WorkFlowNextNodeDTO(workFlowRefundDTO.getEventId(), workFlowRefundDTO.getPreNodeId(),
                workFlowRefundDTO.getCurNodeId(),
                workFlowRefundDTO.getHandlerId(),
                workFlowRefundDTO.getHandlerName(),
                workFlowRefundDTO.getOperator(),
                workFlowRefundDTO.getOperatorName(),
                workFlowRefundDTO.getMessage(),
                Lists.newArrayList(), null);
        this.workFlowEventService.startNextWorkFlow(workFlowNextNodeDTO);
        return new TavernResponse();
    }

    /**
     * 退款处理人
     */
    @GetMapping("/refundOperator")
    public WorkFlowRefundEdgeResponse refundOperatorWorkEvent(@RequestParam(name = "eventId") String eventId,
                                                              @RequestParam(name = "nodeId") String nodeId,
                                                              @RequestParam(name = "direction") String direction) {
        return new WorkFlowRefundEdgeResponse(this.workFlowEventService.getWorkFlowRefundOperator(eventId, nodeId, direction));
    }


    /**
     * 转授权
     */
    @PostMapping("/reChoose")
    public TavernResponse reChooseHandler(@RequestBody List<WorkFlowRefundDTO> workFlowRefundDTOList) throws Exception {
        List<WorkFlowNextNodeDTO> workFlowNextNodeDTOList = workFlowRefundDTOList.parallelStream()
                .map(workFlowRefundDTO -> new WorkFlowNextNodeDTO(workFlowRefundDTO.getEventId(), null,
                        workFlowRefundDTO.getCurNodeId(),
                        workFlowRefundDTO.getHandlerId(),
                        workFlowRefundDTO.getHandlerName(),
                        workFlowRefundDTO.getOperator(),
                        workFlowRefundDTO.getOperatorName(),
                        workFlowRefundDTO.getMessage(),
                        Lists.newArrayList(), null))
                .collect(Collectors.toList());
        this.workFlowEventService.reChooseHandler(workFlowNextNodeDTOList);
        return new TavernResponse();
    }

    /**
     * 转授权对接人员
     */
    @GetMapping("/reChoose/operator")
    public TavernDictResponse getReChooseHandler(@RequestParam(name = "curNodeId") String curNodeId,
                                                 @RequestParam(name = "curOperatorName") String curOperator) {
        return this.workFlowEventService.getReChooseHandler(curNodeId, curOperator);
    }
}
