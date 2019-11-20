package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.crm.service.CrmCompanyBusinessInfoService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderBusinessRelService;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventMapper;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowDelayNotesDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowLogMessageDTO;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowNextNodeDTO;
import com.tuoyou.tavern.crm.workflow.entity.*;
import com.tuoyou.tavern.crm.workflow.service.*;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.common.model.Dict;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.dto.workflow.MyToDoListDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusiness;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;
import com.tuoyou.tavern.protocol.crm.model.workflow.MyTodoListVO;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFlowRefundVO;
import com.tuoyou.tavern.protocol.hrm.spi.HrmUserDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private WorkFlowDefNodeService workFlowDefNodeService;
    @Autowired
    private HrmUserDictService hrmUserDictService;
    @Autowired
    private WorkFlowDefNodeExtAttrService workFlowDefNodeExtAttrService;
    @Autowired
    private CrmCustomOrderBusinessRelService crmCustomOrderBusinessRelService;

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
        workFlowEvent.setCurNodeId(crmOrderBusinessRelDTO.getCurNode());
        switch (crmOrderBusinessRelDTO.getBusinessId()) {
            case "BIZ_1":
                if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate())) {
                    workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                }
                break;
            case "BIZ_2":
                if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate())) {
                    workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                }
                break;
            case "BIZ_3":
                if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getDljzDetail().getDljzBeginDate())) {
                    workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getDljzDetail().getDljzBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                }
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
        startWorkFlowEventHistory.setNextNodeId(crmOrderBusinessRelDTO.getCurNode());
        /*WorkFlowEventHistory curWorkFlowEventHistory = new WorkFlowEventHistory();
        curWorkFlowEventHistory.setEventId(eventId);
        curWorkFlowEventHistory.setBeginDate(workFlowEvent.getBeginDate());
        curWorkFlowEventHistory.setEndDate(LocalDateTime.now());
        curWorkFlowEventHistory.setOperator(crmOrderBusinessRelDTO.getOwnerId());*/


        this.workFlowEventHistoryService.saveWorkFlowEventFirstNodeHistory(startWorkFlowEventHistory, crmOrderBusinessRelDTO.getBusinessId());
//        this.workFlowEventHistoryService.saveWorkFlowEventHistory(curWorkFlowEventHistory, crmOrderBusinessRelDTO.getBusinessId());

        //备注:
        if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getRemark())) {
            WorkFlowLogMessage workFlowLogMessage = new WorkFlowLogMessage();
            workFlowLogMessage.setLogId(UUIDUtil.randomUUID32());
            workFlowLogMessage.setCreateTime(DateUtils.formatDateTime(LocalDateTime.now(), DateUtils.DEFAULT_DATETIME_FORMATTER));
            workFlowLogMessage.setOperator(crmOrderBusinessRelDTO.getCreatorId());
            workFlowLogMessage.setOperatorName(crmOrderBusinessRelDTO.getCreatorName());
            workFlowLogMessage.setMessage(crmOrderBusinessRelDTO.getRemark());
            workFlowLogMessage.setEventId(eventId);
            this.workFlowLogMessageService.saveRootWorkLog(workFlowLogMessage, crmOrderBusinessRelDTO.getBusinessId());
        }

    }

    @TargetDataSource(name = "workflow")
    @Transactional
    @Override
    public void updateWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        //检查是否需要前置条件
        //1. 有：则先检查前置条件有没有结束
        //2. 无：直接
        if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getPreEventId())) {
            //1. 插入前置条件
            WorkFlowEventDependency workFlowEventDependency = new WorkFlowEventDependency();
            workFlowEventDependency.setCreateDate(LocalDateTime.now());
            workFlowEventDependency.setPreEvent(crmOrderBusinessRelDTO.getPreEventId());
            workFlowEventDependency.setNextEvent(eventId);
            this.workFlowEventDependencyService.saveOrUpdate(workFlowEventDependency);
        }

        //1.work_event 建立数据
        WorkFlowEvent workFlowEvent = new WorkFlowEvent();
        workFlowEvent.setEventId(eventId);
        workFlowEvent.setBeginDate(LocalDateTime.now());
        workFlowEvent.setCurOperator(crmOrderBusinessRelDTO.getOwnerId());
        workFlowEvent.setEventOwner(crmOrderBusinessRelDTO.getOwnerId());
        workFlowEvent.setCurOperatorName(crmOrderBusinessRelDTO.getOwner());
        workFlowEvent.setCurNodeId(crmOrderBusinessRelDTO.getCurNode());
        switch (crmOrderBusinessRelDTO.getBusinessId()) {
            case "BIZ_1":
                if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate())) {
                    workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                }
                break;
            case "BIZ_2":
                if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate())) {
                    workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getGjjsbdjDetail().getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                }
                break;
            case "BIZ_3":
                if (StringUtils.isNotEmpty(crmOrderBusinessRelDTO.getDljzDetail().getDljzBeginDate())) {
                    workFlowEvent.setBeginDate(DateUtils.parseDateTime(crmOrderBusinessRelDTO.getDljzDetail().getDljzBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                }
                break;
        }
        this.workFlowEventService.updateById(workFlowEvent);

    }

    @TargetDataSource(name = "workflow")
    @Transactional
    @Override
    public void removeWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        this.workFlowEventDependencyService.removeById(crmOrderBusinessRelDTO.getPreEventId());
        this.workFlowEventService.removeById(eventId);

    }

    @TargetDataSource(name = "workflow")
    @Override
    public int saveWorkFlowEvent(WorkFlowEvent workFlowEvent, String businessId) {
        return this.baseMapper.insertWorkFlowEvent(workFlowEvent, businessId);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public IPage<MyTodoListVO> getMyPendingWork(Page page, MyToDoListDTO myToDoListDTO) {
        IPage<MyTodoListVO> iPage = this.baseMapper.selectMyPendingWork(page, myToDoListDTO);
        return getMyTodoListVOIPage(iPage);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public IPage<MyTodoListVO> getAllWorkEvent(Page page, MyToDoListDTO myToDoListDTO) {
        IPage<MyTodoListVO> iPage = this.baseMapper.selectAllWorkEvent(page, myToDoListDTO);
        return getMyTodoListVOIPage(iPage);
    }

    private IPage<MyTodoListVO> getMyTodoListVOIPage(IPage<MyTodoListVO> iPage) {
        Map<String, String> userMap = this.hrmUserDictService.queryStaffByRole("")
                .getData()
                .parallelStream()
                .collect(Collectors.toMap(Dict::getId, Dict::getName));
        List<MyTodoListVO> myTodoListVOList = iPage.getRecords()
                .stream()
                .peek(vo -> {
                    if (StringUtils.isNotEmpty(vo.getPreSalesId()) && userMap.containsKey(vo.getPreSalesId())) {
                        vo.setPreSales(userMap.get(vo.getPreSalesId()));
                    }
                }).collect(Collectors.toList());
        iPage.setRecords(myTodoListVOList);
        return iPage;
    }

    @Transactional
    @TargetDataSource(name = "workflow")
    @Override
    public void delayWorkEvent(WorkFlowDelayNotesDTO workFlowDelayNotesDTO) {
        String logId = UUIDUtil.randomUUID32();
        WorkFlowLogMessage workFlowLogMessage = new WorkFlowLogMessage();
        workFlowLogMessage.setLogId(logId);
        workFlowLogMessage.setOperator(workFlowDelayNotesDTO.getOperator());
        workFlowLogMessage.setOperatorName(workFlowDelayNotesDTO.getOperatorName());
        workFlowLogMessage.setCreateTime(DateUtils.formatDateTime(LocalDateTime.now(), DateUtils.DEFAULT_DATETIME_FORMATTER));
        workFlowLogMessage.setMessage(workFlowDelayNotesDTO.getMessage());
        workFlowLogMessage.setEventId(workFlowDelayNotesDTO.getEventId());
        this.workFlowLogMessageService.save(workFlowLogMessage);
            /*CrmCompanyBusiness crmCompanyBusiness = this.crmCompanyBusinessInfoService.getOne(Wrappers.<CrmCompanyBusiness>query().lambda()
                    .eq(CrmCompanyBusiness::getBusinessId, workFlowDelayNotesDTO.getBusinessId())
                    .eq(CrmCompanyBusiness::getCompanyId, workFlowDelayNotesDTO.getCompanyId()));
            if (Objects.nonNull(crmCompanyBusiness)) {
                crmCompanyBusiness.setEndDate(crmCompanyBusiness.getEndDate().plusDays(workFlowDelayNotesDTO.getDelayDays()));
                crmCompanyBusiness.setUpdateDate(LocalDateTime.now());
                this.crmCompanyBusinessInfoService.update(crmCompanyBusiness, Wrappers.<CrmCompanyBusiness>query().lambda()
                        .eq(CrmCompanyBusiness::getBusinessId, workFlowDelayNotesDTO.getBusinessId())
                        .eq(CrmCompanyBusiness::getCompanyId, workFlowDelayNotesDTO.getCompanyId()));
            }*/
        WorkFlowEvent workFlowEvent = this.workFlowEventService.getById(workFlowDelayNotesDTO.getEventId());
        workFlowEvent.setEndDate(workFlowEvent.getEndDate().plusDays(workFlowDelayNotesDTO.getDelayDays()));
        this.workFlowEventService.updateById(workFlowEvent);
    }

    @Transactional
    @TargetDataSource(name = "workflow")
    @Override
    public void startNextWorkFlow(WorkFlowNextNodeDTO workFlowNextNodeDTO) throws Exception {
        //更新event状态
        //更新eventhis状态
        //判断是否有dependency，发起下一个任务
        WorkFlowEvent workFlowEvent = this.getById(workFlowNextNodeDTO.getEventId());
        WorkFlowDefNodeExtAttr workFlowDefNodeExtAttr = this.workFlowDefNodeExtAttrService.getById(workFlowNextNodeDTO.getCurNodeId());
        Integer restDays = StringUtils.isEmpty(workFlowDefNodeExtAttr.getRestDays()) ? 2 : Integer.parseInt(workFlowDefNodeExtAttr.getRestDays());
        LocalDateTime beginDate = workFlowEvent.getBeginDate();
        workFlowEvent.setCurOperator(workFlowNextNodeDTO.getCurOperator());
        workFlowEvent.setCurOperatorName(workFlowNextNodeDTO.getCurOperatorName());
        workFlowEvent.setCurNodeId(workFlowNextNodeDTO.getCurNodeId());
        workFlowEvent.setBeginDate(LocalDateTime.now());
        if (restDays != null) {
            workFlowEvent.setEndDate(LocalDateTime.now().plusDays(restDays));
        }
        this.updateById(workFlowEvent);


        WorkFlowEventHistory curWorkFlowEventHistory = new WorkFlowEventHistory();
        curWorkFlowEventHistory.setEventId(workFlowEvent.getEventId());
        curWorkFlowEventHistory.setBeginDate(beginDate);
        curWorkFlowEventHistory.setEndDate(LocalDateTime.now());
        curWorkFlowEventHistory.setOperator(workFlowNextNodeDTO.getOperator());
        curWorkFlowEventHistory.setGraphId(workFlowEvent.getGraphId());
        curWorkFlowEventHistory.setNodeId(workFlowNextNodeDTO.getPreNodeId());
        curWorkFlowEventHistory.setNextNodeId(workFlowNextNodeDTO.getCurNodeId());
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
            workFlowLogMessageDTO.setCurNodeId(workFlowNextNodeDTO.getPreNodeId());
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

        //更新第三方消息
        if (workFlowNextNodeDTO.getThirdPartyFlag().equals("0")) {
            return;
        }
        CrmOrderBusinessRel crmOrderBusinessRel = new CrmOrderBusinessRel();
        crmOrderBusinessRel.setEventId(workFlowNextNodeDTO.getEventId());
        crmOrderBusinessRel.setThirdPartyFee(StringUtils.isEmpty(workFlowNextNodeDTO.getThirdPartyFee()) ? null : new BigDecimal(workFlowNextNodeDTO.getThirdPartyFee()));
        crmOrderBusinessRel.setNeedThirdParty("1");
        crmOrderBusinessRel.setThirdPartyId(workFlowNextNodeDTO.getThirdPartyId());
        crmOrderBusinessRel.setThirdPartyId(workFlowNextNodeDTO.getThirdPartyId());
        this.crmCustomOrderBusinessRelService.updateThirdPartyInfo(crmOrderBusinessRel);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public void reChooseHandler(List<WorkFlowNextNodeDTO> workFlowNextNodeDTOList) throws Exception {
        for (WorkFlowNextNodeDTO workFlowNextNodeDTO : workFlowNextNodeDTOList) {

            WorkFlowEvent workFlowEvent = this.getById(workFlowNextNodeDTO.getEventId());
            String preOperator = workFlowEvent.getCurOperator();
            workFlowEvent.setCurOperator(workFlowNextNodeDTO.getCurOperator());
            workFlowEvent.setCurOperatorName(workFlowNextNodeDTO.getCurOperatorName());
            workFlowEvent.setCurNodeId(workFlowNextNodeDTO.getCurNodeId());
            workFlowEvent.setBeginDate(LocalDateTime.now());
            this.updateById(workFlowEvent);

            WorkFlowEventHistory curWorkFlowEventHistory = new WorkFlowEventHistory();
            curWorkFlowEventHistory.setEventId(workFlowEvent.getEventId());
            curWorkFlowEventHistory.setOperator(workFlowNextNodeDTO.getCurOperator());
            curWorkFlowEventHistory.setGraphId(workFlowEvent.getGraphId());
            curWorkFlowEventHistory.setNodeId(workFlowNextNodeDTO.getPreNodeId());
            curWorkFlowEventHistory.setNextNodeId(workFlowNextNodeDTO.getCurNodeId());
            this.workFlowEventHistoryService.update(curWorkFlowEventHistory,
                    Wrappers.<WorkFlowEventHistory>query()
                            .lambda()
                            .eq(WorkFlowEventHistory::getEventId, curWorkFlowEventHistory.getEventId())
                            .eq(WorkFlowEventHistory::getGraphId, curWorkFlowEventHistory.getGraphId())
                            .eq(WorkFlowEventHistory::getOperator, preOperator)
                            .eq(WorkFlowEventHistory::getNextNodeId, curWorkFlowEventHistory.getNextNodeId())
            );

            //携带备注信息

            WorkFlowLogMessage workFlowLogMessage = new WorkFlowLogMessage();
            workFlowLogMessage.setLogId(UUIDUtil.randomUUID32());
            workFlowLogMessage.setOperator(workFlowNextNodeDTO.getOperator());
            workFlowLogMessage.setOperatorName(workFlowNextNodeDTO.getOperatorName());
            workFlowLogMessage.setMessage(workFlowNextNodeDTO.getMessage());
            workFlowLogMessage.setEventId(workFlowNextNodeDTO.getEventId());
            workFlowLogMessage.setCreateTime(DateUtils.formatDateTime(LocalDateTime.now(), DateUtils.DEFAULT_DATETIME_FORMATTER));
            this.workFlowLogMessageService.save(workFlowLogMessage);
        }
    }

    @TargetDataSource(name = "workflow")
    @Override
    public TavernDictResponse getReChooseHandler(String curNodeId, String curNodeName) {
        WorkFlowDefNode workFlowDefNode = this.workFlowDefNodeService.getByNodeId(curNodeId);
        TavernDictResponse dictResponse = this.hrmUserDictService.queryStaffByRole(workFlowDefNode.getRole());
        List<Dict> dictList = dictResponse.getData().stream().filter(dict -> !dict.getName().equals(curNodeName)).collect(Collectors.toList());
        return new TavernDictResponse(dictList);
    }

    @TargetDataSource(name = "workflow")
    @Override
    public List<WorkFlowRefundVO> getWorkFlowRefundOperator(String eventId, String nodeId, String direction) {
        WorkFlowNodeEdge workFlowNodeEdge;
        if (StringUtils.endsWith(direction, "0")) {
            workFlowNodeEdge = this.baseMapper.selectPreEdge(eventId, nodeId);
        } else {
            workFlowNodeEdge = this.baseMapper.selectNextEdge(eventId, nodeId);
        }
        if (Objects.isNull(workFlowNodeEdge)) {
            return Lists.newArrayList();
        }
        TavernDictResponse response = this.hrmUserDictService.queryStaffByRole(workFlowNodeEdge.getRole());
        if (Objects.isNull(response) || response.getData().size() == 0) {
            return Lists.newArrayList();
        }
        List<WorkFlowRefundVO> workFlowRefundVOList = response.getData()
                .stream()
                .map(dict -> {
                    WorkFlowRefundVO workFlowRefundVO = new WorkFlowRefundVO();
                    workFlowRefundVO.setCurNodeId(workFlowNodeEdge.getCurNodeId());
                    workFlowRefundVO.setPreNodeId(workFlowNodeEdge.getPreNodeId());
                    workFlowRefundVO.setId(dict.getId());
                    workFlowRefundVO.setName(dict.getName());
                    return workFlowRefundVO;
                }).collect(Collectors.toList());

        return workFlowRefundVOList;
    }
}
