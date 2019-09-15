package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.FileUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowLogMessageMapper;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogAttachment;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowLogMessage;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
public class WorkFlowLogMessageServiceImpl extends ServiceImpl<WorkFlowLogMessageMapper, WorkFlowLogMessage> implements WorkFlowLogMessageService {

    @Value("${workflow.log.url.path:http://127.0.0.1:80/workflow/log/attachment/}")
    private String workFlowLogUrlPath;
    @Value("${workflow.log.path:/mnt/file/workflow/log}")
    private String workFlowLogPath;
    @Autowired
    private WorkFlowLogAttachmentService workFlowLogAttachmentService;
    @Autowired
    private WorkFlowLogMessageService workFlowLogMessageService;

    @TargetDataSource(name = "workflow")
    @Override
    public IPage<WorkFlowLogVO> getWorkFlowLogMessagePage(Page page, WorkFlowLogQueryDTO workFlowLogQueryDTO) {
        return this.baseMapper.selectWorkFlowLogMessagePage(page, workFlowLogQueryDTO);
    }

    @Transactional
    @TargetDataSource(name = "workflow")
    @Override
    public void saveWorkFlowLog(WorkFlowLogMessageDTO workFlowLogMessageDTO) throws Exception {
        String logId = UUIDUtil.randomUUID32();
        WorkFlowLogMessage workFlowLogMessage = new WorkFlowLogMessage();
        BeanUtils.copyProperties(workFlowLogMessageDTO, workFlowLogMessage);
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
        Map<String, List<WorkFlowGraphLog>> workFlowGraphLogMap = workFlowGraphLogList
                .stream()
                .collect(Collectors.groupingBy(WorkFlowGraphLog::getNodeId));
        List<WorkFLowNodes> nodes = workFlowGraphLogMap.entrySet().stream()
                .map(entry -> {
                    WorkFlowGraphLog log = entry.getValue().get(0);
                    return new WorkFLowNodes(log.getNodeId(), log.getName(), log.getOperator());
                }).collect(Collectors.toList());
        List<WorkFLowEdges> edges = workFlowGraphLogList
                .stream()
                .filter(log -> workFlowGraphLogMap.containsKey(log.getTargetNode()))
                .map(log -> new WorkFLowEdges(log.getSourceNode(), log.getTargetNode()))
                .collect(Collectors.toList());
        return new WorkFlowGraphLogVO(nodes, edges);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public boolean save(WorkFlowLogMessage entity) {
        return super.save(entity);
    }
}
