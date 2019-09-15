package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.crm.service.CrmCompanyBusinessInfoService;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventMapper;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowDelayNotesDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowNextNodeDTO;
import com.tuoyou.tavern.crm.workflow.entity.*;
import com.tuoyou.tavern.crm.workflow.service.*;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.dto.workflow.MyToDoListDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusiness;
import com.tuoyou.tavern.protocol.crm.model.workflow.MyTodoListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
public class WorkFlowEventServiceImpl extends ServiceImpl<WorkFlowEventMapper, WorkFlowEvent> implements WorkFlowEventService {

    @Autowired
    private WorkFlowEventService workFlowEventService;
    @Autowired
    private WorkFlowEventHistoryService workFlowEventHistoryService;
    @Autowired
    private WorkFlowEventDependencyService workFlowEventDependencyService;
    @Autowired
    private WorkFlowEventDependencyHisService workFlowEventDependencyHisService;
    @Autowired
    private WorkFlowLogMessageService workFlowLogMessageService;

    @Autowired
    private CrmCompanyBusinessInfoService crmCompanyBusinessInfoService;

    @TargetDataSource(name = "workflow")
    @Transactional
    @Override
    public void startWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        //检查是否需要前置条件
        //1. 有：则先检查前置条件有没有结束
        //2. 无：直接
        if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getPreEventId())) {
            //1. 插入前置条件
            WorkFlowEventDependency workFlowEventDependency = new WorkFlowEventDependency();
            workFlowEventDependency.setCreateDate(LocalDateTime.now());
            workFlowEventDependency.setPreEvent(crmOrderBusinessRelDTO.getPreEventId());
            workFlowEventDependency.setNextEvent(eventId);
            this.workFlowEventDependencyService.save(workFlowEventDependency);
//            return;
        }

        //1.work_event 建立数据
        WorkFlowEvent workFlowEvent = new WorkFlowEvent();
        workFlowEvent.setEventId(eventId);
        workFlowEvent.setBeginDate(LocalDateTime.now());
        workFlowEvent.setCurOperator(crmOrderBusinessRelDTO.getOwnerId());
        workFlowEvent.setEventOwner(crmOrderBusinessRelDTO.getOwnerId());
        workFlowEvent.setCurOperatorName(crmOrderBusinessRelDTO.getOwner());
        switch (crmOrderBusinessRelDTO.getBusinessId()) {
            case "BIZ_1":
                workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                break;
            case "BIZ_2":
                workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                break;
            case "BIZ_3":
                workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getDljzDetail().getDljzBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                break;
        }
        this.workFlowEventService.saveWorkFlowEvent(workFlowEvent, crmOrderBusinessRelDTO.getBusinessId());
        //2.work_event_his
        //新增开始节点信息
        //新增对接节点信息
        WorkFlowEventHistory startWorkFlowEventHistory = new WorkFlowEventHistory();
        startWorkFlowEventHistory.setEventId(eventId);
        startWorkFlowEventHistory.setBeginDate(workFlowEvent.getBeginDate());
        startWorkFlowEventHistory.setEndDate(LocalDateTime.now());
        startWorkFlowEventHistory.setOperator(crmOrderBusinessRelDTO.getCreatorId());
        WorkFlowEventHistory curWorkFlowEventHistory = new WorkFlowEventHistory();
        curWorkFlowEventHistory.setEventId(eventId);
        curWorkFlowEventHistory.setBeginDate(workFlowEvent.getBeginDate());
        curWorkFlowEventHistory.setEndDate(LocalDateTime.now());
        curWorkFlowEventHistory.setOperator(crmOrderBusinessRelDTO.getOwnerId());

        this.workFlowEventHistoryService.saveWorkFlowEventFirstNodeHistory(startWorkFlowEventHistory, crmOrderBusinessRelDTO.getBusinessId());
        this.workFlowEventHistoryService.saveWorkFlowEventHistory(curWorkFlowEventHistory, crmOrderBusinessRelDTO.getBusinessId());

    }

    @TargetDataSource(name = "workflow")
    @Override
    public int saveWorkFlowEvent(WorkFlowEvent workFlowEvent, String businessId) {
        return this.baseMapper.insertWorkFlowEvent(workFlowEvent, businessId);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public IPage<MyTodoListVO> getMyPendingWork(Page page, MyToDoListDTO myToDoListDTO) {
        return this.baseMapper.selectMyPendingWork(page, myToDoListDTO);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public IPage<MyTodoListVO> getAllWorkEvent(Page page, MyToDoListDTO myToDoListDTO) {
        return this.baseMapper.selectAllWorkEvent(page, myToDoListDTO);
    }

    @Transactional
    @TargetDataSource(name = "workflow")
    @Override
    public void startNextWorkFlow(WorkFlowNextNodeDTO workFlowNextNodeDTO) throws Exception {
        //更新event状态
        //更新eventhis状态
        //判断是否有dependency，发起下一个任务
        WorkFlowEvent workFlowEvent = this.getById(workFlowNextNodeDTO.getEventId());
        workFlowEvent.setCurOperator(workFlowNextNodeDTO.getCurOperator());
        workFlowEvent.setCurOperatorName(workFlowNextNodeDTO.getCurOperatorName());
        workFlowEvent.setCurNodeId(workFlowNextNodeDTO.getCurNodeId());
        workFlowEvent.setBeginDate(LocalDateTime.now());
        this.updateById(workFlowEvent);

        WorkFlowEventHistory curWorkFlowEventHistory = new WorkFlowEventHistory();
        curWorkFlowEventHistory.setEventId(workFlowEvent.getEventId());
        curWorkFlowEventHistory.setBeginDate(LocalDateTime.now());
        curWorkFlowEventHistory.setOperator(workFlowNextNodeDTO.getCurOperator());
        curWorkFlowEventHistory.setGraphId(workFlowEvent.getGraphId());
        curWorkFlowEventHistory.setNodeId(workFlowNextNodeDTO.getCurNodeId());
        this.workFlowEventHistoryService.save(curWorkFlowEventHistory);

        //携带备注信息

        if (StringUtils.isNotEmpty(workFlowNextNodeDTO.getMessage()) || workFlowNextNodeDTO.getRefundFee() != null || workFlowNextNodeDTO.getFiles().size() != 0) {
            WorkFlowLogMessageDTO workFlowLogMessageDTO = new WorkFlowLogMessageDTO();
            workFlowLogMessageDTO.setOperator(workFlowNextNodeDTO.getOperator());
            workFlowLogMessageDTO.setOperatorName(workFlowNextNodeDTO.getOperatorName());
            workFlowLogMessageDTO.setMessage(workFlowNextNodeDTO.getMessage());
            workFlowLogMessageDTO.setEventId(workFlowNextNodeDTO.getEventId());
            workFlowLogMessageDTO.setFiles(workFlowNextNodeDTO.getFiles());
            workFlowLogMessageDTO.setRefundFee(workFlowNextNodeDTO.getRefundFee());
            this.workFlowLogMessageService.saveWorkFlowLog(workFlowLogMessageDTO);
        }


        WorkFlowEventDependency workFlowEventDependency = this.workFlowEventDependencyService.getWorkFlowEventDependency(workFlowEvent.getEventId());
        if (Objects.nonNull(workFlowEventDependency)) {
            //判断当前节点是否为结束节点
            //1.删除dependency
            //2.拉入历史
            //3.改造：查询当前任务时，需要过滤有dependency的
            this.workFlowEventDependencyService.removeById(workFlowEvent.getEventId());
            WorkFlowEventDependencyHis workFlowEventDependencyHis = new WorkFlowEventDependencyHis();
            BeanUtils.copyProperties(workFlowEventDependency, workFlowEventDependencyHis);
            workFlowEventDependencyHis.setFinishTime(LocalDateTime.now());
            this.workFlowEventDependencyHisService.save(workFlowEventDependencyHis);
        }


    }

    @Override
    public void delayWorkEvent(WorkFlowDelayNotesDTO workFlowDelayNotesDTO) {
        String logId = UUIDUtil.randomUUID32();
        try {
            WorkFlowLogMessage workFlowLogMessage = new WorkFlowLogMessage();
            workFlowLogMessage.setLogId(logId);
            workFlowLogMessage.setOperator(workFlowDelayNotesDTO.getOperator());
            workFlowLogMessage.setOperatorName(workFlowDelayNotesDTO.getOperatorName());
            workFlowLogMessage.setCreateTime(DateUtils.formatDateTime(LocalDateTime.now(), DateUtils.DEFAULT_DATETIME_FORMATTER));
            workFlowLogMessage.setMessage(workFlowDelayNotesDTO.getMessage());
            workFlowLogMessage.setEventId(workFlowDelayNotesDTO.getEventId());
            this.workFlowLogMessageService.save(workFlowLogMessage);
            CrmCompanyBusiness crmCompanyBusiness = this.crmCompanyBusinessInfoService.getOne(Wrappers.<CrmCompanyBusiness>query().lambda()
                    .eq(CrmCompanyBusiness::getBusinessId, workFlowDelayNotesDTO.getBusinessId())
                    .eq(CrmCompanyBusiness::getCompanyId, workFlowDelayNotesDTO.getCompanyId()));
            if (Objects.nonNull(crmCompanyBusiness)) {
                crmCompanyBusiness.setEndDate(crmCompanyBusiness.getEndDate().plusDays(workFlowDelayNotesDTO.getDelayDays()));
                crmCompanyBusiness.setUpdateDate(LocalDateTime.now());
                this.crmCompanyBusinessInfoService.update(crmCompanyBusiness, Wrappers.<CrmCompanyBusiness>query().lambda()
                        .eq(CrmCompanyBusiness::getBusinessId, workFlowDelayNotesDTO.getBusinessId())
                        .eq(CrmCompanyBusiness::getCompanyId, workFlowDelayNotesDTO.getCompanyId()));
            }
        } catch (Exception e) {
            this.workFlowLogMessageService.removeById(logId);
            throw e;
        }

    }
}
