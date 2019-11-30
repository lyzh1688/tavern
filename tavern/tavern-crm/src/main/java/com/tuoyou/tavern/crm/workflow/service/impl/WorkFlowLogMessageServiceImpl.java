package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.FileUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.util.ListUtils;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowLogMessageMapper;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogAttachment;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowDefNodeService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogAttachmentService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowLogMessageService;
import com.tuoyou.tavern.protocol.crm.dto.workflow.WorkFlowLogQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.workflow.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
public class WorkFlowLogMessageServiceImpl extends ServiceImpl<WorkFlowLogMessageMapper, WorkFlowLogMessage> implements WorkFlowLogMessageService {

    @Value("${workflow.log.url.path:http://119.3.19.171:80/workflow/log/attachment/}")
    private String workFlowLogUrlPath;
    @Value("${workflow.log.path:/mnt/file/workflow/log/}")
    private String workFlowLogPath;
    @Autowired
    private WorkFlowLogAttachmentService workFlowLogAttachmentService;
    @Autowired
    private WorkFlowDefNodeService workFlowDefNodeService;

    @TargetDataSource(name = "workflow")
    @Override
    public IPage<WorkFlowLogVO> getWorkFlowLogMessagePage(Page page, WorkFlowLogQueryDTO workFlowLogQueryDTO) {
        return this.baseMapper.selectWorkFlowLogMessagePage(page,
                workFlowLogQueryDTO.getEventId(),
                StringUtils.isEmpty(workFlowLogQueryDTO.getOperator()) ? null : Arrays.asList(StringUtils.split(workFlowLogQueryDTO.getOperator(), ",")),
                workFlowLogQueryDTO.getCurNodeId()
        );
    }

    @Transactional
    @TargetDataSource(name = "workflow")
    @Override
    public void saveWorkFlowLog(WorkFlowLogMessageDTO workFlowLogMessageDTO) throws Exception {
        String logId = UUIDUtil.randomUUID32();
        WorkFlowLogMessage workFlowLogMessage = new WorkFlowLogMessage();
        BeanUtils.copyProperties(workFlowLogMessageDTO, workFlowLogMessage);
        workFlowLogMessage.setNodeId(workFlowLogMessageDTO.getCurNodeId());
        workFlowLogMessage.setLogId(logId);
        workFlowLogMessage.setCreateTime(DateUtils.formatDateTime(LocalDateTime.now(), DateUtils.DEFAULT_DATETIME_FORMATTER));
        if (!workFlowLogMessageDTO.getFiles().isEmpty()) {
            String eventWorkFlowLogUrlPath = StringUtils.join(workFlowLogUrlPath,
                    workFlowLogMessageDTO.getEventId(),
                    "/",
                    workFlowLogMessageDTO.getOperator(),
                    "/");
            String eventWorkFlowLogPath = StringUtils.join(workFlowLogPath,
                    workFlowLogMessageDTO.getEventId(),
                    "/",
                    workFlowLogMessageDTO.getOperator(),
                    "/");
            workFlowLogMessage.setHasAttachment("1");
            workFlowLogMessage.setAttachmentsPath(eventWorkFlowLogUrlPath);


            List<WorkFlowLogAttachment> workFlowLogAttachment = workFlowLogMessageDTO.getFiles()
                    .stream()
                    .map(file -> {
                        String filePath = StringUtils.join(eventWorkFlowLogUrlPath,
                                "/",
                                file.getName());
                        WorkFlowLogAttachment tmpAttachment = new WorkFlowLogAttachment();
                        tmpAttachment.setCreateTime(DateUtils.formatDateTime(LocalDateTime.now(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                        tmpAttachment.setFileId(UUIDUtil.randomUUID32());
                        tmpAttachment.setLogId(logId);
                        tmpAttachment.setFilePath(filePath);
                        return tmpAttachment;
                    }).collect(Collectors.toList());
            for (MultipartFile file : workFlowLogMessageDTO.getFiles()) {
                FileUtils.multiPartFileWriter(file, eventWorkFlowLogPath);
            }

            this.workFlowLogAttachmentService.saveWorkFlowLogAttachmentBatch(workFlowLogAttachment);
        }
        if (Objects.nonNull(workFlowLogMessageDTO.getRefundFee())) {
            workFlowLogMessage.setHasRefund("1");
            workFlowLogMessage.setRefundFee(workFlowLogMessageDTO.getRefundFee());
        }
        this.save(workFlowLogMessage);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public WorkFlowGraphLogVO getWorkFlowGraphLog(String eventId) {
        List<WorkFlowGraphLog> workFlowGraphLogList = this.baseMapper.selectWorkFlowGraphLog(eventId);
        Map<String, List<WorkFlowGraphLog>> workLogMap = workFlowGraphLogList.stream()
                .collect(Collectors.groupingBy(WorkFlowGraphLog::getSourceNode));
        List<String> idList = workFlowGraphLogList.parallelStream()
                .flatMap(log -> {
                    if (StringUtils.isNoneEmpty(log.getTargetNode())) {
                        return Stream.of(log.getTargetNode(), log.getSourceNode());
                    }
                    return Stream.of(log.getSourceNode());
                })
                .distinct()
                .collect(Collectors.toList());
        List<WorkFLowNodes> nodes = this.workFlowDefNodeService.getList(idList)
                .stream()
                .map(workFlowDefNode -> {
                    if (workLogMap.containsKey(workFlowDefNode.getNodeId())) {
                        List<String> operators = workLogMap.get(workFlowDefNode.getNodeId()).stream().map(WorkFlowGraphLog::getOperator).collect(Collectors.toList());
                        return new WorkFLowNodes(workFlowDefNode.getNodeId(), workFlowDefNode.getName(), StringUtils.join(operators, ","));
                    }
                    return new WorkFLowNodes(workFlowDefNode.getNodeId(), workFlowDefNode.getName(), "");
                }).collect(Collectors.toList());
      /*  Map<String, String> edgeMap = workFlowGraphLogList.parallelStream()
                .filter(workFlowGraphLog -> StringUtils.isNoneEmpty(workFlowGraphLog.getTargetNode()))
                .map(WorkFlowGraphLog::getSourceNode)
                .distinct()
                .collect(Collectors.toMap(k -> k, v -> v));
        Map<String, List<WorkFlowGraphLog>> workFlowGraphLogMap = workFlowGraphLogList
                .stream()
                .collect(Collectors.groupingBy(WorkFlowGraphLog::getNodeId));
        List<WorkFLowNodes> nodes = workFlowGraphLogMap.entrySet().stream()
                .map(entry -> {
                    WorkFlowGraphLog log = entry.getValue().get(0);
                    return new WorkFLowNodes(log.getNodeId(), log.getName(), log.getOperator());
                }).collect(Collectors.toList());*/

        /*List<WorkFLowEdges> edges = workFlowGraphLogList
                .stream()
                .filter(workFlowGraphLog -> edgeMap.containsKey(workFlowGraphLog.getTargetNode()) || StringUtils.isEmpty(workFlowGraphLog.getTargetNode()))
                .map(log -> *//*{
                    if (entry.getValue().size() == 1) {
                        WorkFlowGraphLog log = entry.getValue().get(0);
                        return Collections.singleton(new WorkFLowNodes(log.getNodeId(), log.getName(), log.getOperator())).stream();
                    }
                    List<WorkFlowGraphLog> tmpWorkFlowGraphLogList = entry.getValue();
                    List<WorkFLowNodes> tmpWorkFLowNodesList = tmpWorkFlowGraphLogList.stream()
                            .filter(workFlowGraphLog -> edgeMap.containsKey(workFlowGraphLog.getTargetNode()))
                            .map(log -> new WorkFLowNodes(log.getNodeId(), log.getName(), log.getOperator()))
                            .collect(Collectors.toList());
                    return tmpWorkFLowNodesList.stream();

                }*//*new WorkFLowEdges(log.getSourceNode(), log.getTargetNode()))
                .collect(Collectors.toList());*/
        List<WorkFLowEdges> edges = workFlowGraphLogList.stream()
                .map(workFlowGraphLog -> new WorkFLowEdges(workFlowGraphLog.getSourceNode(), workFlowGraphLog.getTargetNode()))
                .filter(workFLowEdges -> StringUtils.isNoneEmpty(workFLowEdges.getTarget()))
                .distinct()
                .collect(Collectors.toList());
        ListUtils.sortWorkFLowNodesFirstIndex(nodes, "登记", "公司注册");
        ListUtils.sortWorkFLowEdgesFirstIndex(edges, "sq", "dj");

        return new WorkFlowGraphLogVO(nodes, edges);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public int saveRootWorkLog(WorkFlowLogMessage workFlowLogMessage, String businessId) {
        return this.baseMapper.insertRootWorkLog(workFlowLogMessage, businessId);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public boolean save(WorkFlowLogMessage entity) {
        return super.save(entity);
    }


}
