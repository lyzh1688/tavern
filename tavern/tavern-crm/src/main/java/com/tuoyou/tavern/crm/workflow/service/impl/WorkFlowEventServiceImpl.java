package com.tuoyou.tavern.crm.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.crm.workflow.dao.WorkFlowEventMapper;
import com.tuoyou.tavern.crm.workflow.dto.WorkFlowNextNodeDTO;
import com.tuoyou.tavern.protocol.crm.dto.workflow.MyToDoListDTO;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEvent;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventDependency;
import com.tuoyou.tavern.crm.workflow.entity.WorkFlowEventHistory;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventDependencyService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventHistoryService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventService;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.model.workflow.MyTodoListVO;
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
    public void startNextWorkFlow(WorkFlowNextNodeDTO workFlowNextNodeDTO) {
        //更新event状态
        //更新eventhis状态
        //判断是否有dependency，发起下一个任务
        WorkFlowEvent workFlowEvent = this.getById(workFlowNextNodeDTO.getEventId());
        workFlowEvent.setCurOperator(workFlowNextNodeDTO.getCurOperator());
        workFlowEvent.setCurNodeId(workFlowNextNodeDTO.getCurNodeId());
        workFlowEvent.setBeginDate(LocalDateTime.now());
        this.updateById(workFlowEvent);

        WorkFlowEventHistory curWorkFlowEventHistory = new WorkFlowEventHistory();
        curWorkFlowEventHistory.setEventId(workFlowEvent.getEventId());
        curWorkFlowEventHistory.setBeginDate(LocalDateTime.now());
        curWorkFlowEventHistory.setOperator(workFlowNextNodeDTO.getCurOperator());
        curWorkFlowEventHistory.setGraphId(workFlowEvent.getEventId());
        curWorkFlowEventHistory.setNodeId(workFlowNextNodeDTO.getCurNodeId());
        this.workFlowEventHistoryService.save(curWorkFlowEventHistory);

        WorkFlowEventDependency workFlowEventDependency = this.workFlowEventDependencyService.getById(workFlowEvent.getEventId());
        if (Objects.nonNull(workFlowEventDependency)){
            //判断当前节点是否为结束节点
            //1.删除dependency
            //2.拉入历史
            //3.改造：查询当前任务时，需要过滤有dependency的

        }


    }
}
