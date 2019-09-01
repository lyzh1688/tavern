package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.crm.dao.CrmOrderBusinessRelMapper;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderBusinessRelService;
import com.tuoyou.tavern.crm.crm.service.CrmOrderDljzDetailService;
import com.tuoyou.tavern.crm.crm.service.CrmOrderGjjsbdjDetailService;
import com.tuoyou.tavern.crm.crm.service.CrmOrderGszcDetailService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventService;
import com.tuoyou.tavern.protocol.crm.dto.*;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderDljzDetail;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderGjjsbdjDetail;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderGszcDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Slf4j
@Service
public class CrmCustomOrderBusinessRelServiceImpl extends ServiceImpl<CrmOrderBusinessRelMapper, CrmOrderBusinessRel> implements CrmCustomOrderBusinessRelService {

    @Autowired
    private CrmOrderDljzDetailService crmOrderDljzDetailService;
    @Autowired
    private CrmOrderGjjsbdjDetailService crmOrderGjjsbdjDetailService;
    @Autowired
    private CrmOrderGszcDetailService crmOrderGszcDetailService;
    @Autowired
    private CrmCustomOrderBusinessRelService crmCustomOrderBusinessRelService;
    @Autowired
    private WorkFlowEventService workFlowEventService;


    @Override
    public void saveOrderBusinessAndStartWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO) {
        String eventId = UUIDUtil.randomUUID32();
        try {
            this.crmCustomOrderBusinessRelService.saveOrderBusiness(crmOrderBusinessRelDTO, eventId);
            //新增workflow
            this.workFlowEventService.startWorkFlow(crmOrderBusinessRelDTO, eventId);
        } catch (Exception e) {
            this.crmCustomOrderBusinessRelService.rollBackOrderBusiness(crmOrderBusinessRelDTO, eventId);
            throw e;
        }

    }

    @Transactional
    @Override
    public void saveOrderBusiness(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        try {
            //录入订单
            CrmOrderBusinessRel crmOrderBusinessRel = new CrmOrderBusinessRel();
            BeanUtils.copyProperties(crmOrderBusinessRelDTO, crmOrderBusinessRel);
            crmOrderBusinessRel.setEventId(eventId);
            crmOrderBusinessRel.setCreateDate(LocalDateTime.now());
            this.save(crmOrderBusinessRel);
            log.info("add new business {}", crmOrderBusinessRelDTO.getBusinessId());
            switch (crmOrderBusinessRelDTO.getBusinessId()) {
                case "BIZ_1":
                    this.cvtCrmOrderGjjsbdjDetail(crmOrderBusinessRelDTO, eventId);
                    break;
                case "BIZ_2":
                    this.cvtCrmOrderGjjsbdjDetail(crmOrderBusinessRelDTO, eventId);
                    break;
                case "BIZ_3":
                    CrmOrderDljzDetail crmOrderDljzDetail = new CrmOrderDljzDetail();
                    DljzDetail dljzDetail = crmOrderBusinessRelDTO.getDljzDetail();
                    crmOrderDljzDetail.setEventId(eventId);
                    crmOrderDljzDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
                    crmOrderDljzDetail.setIsBegin(dljzDetail.getIsBegin());
                    crmOrderDljzDetail.setBeginDate(DateUtils.parseDate(dljzDetail.getDljzBeginDate(), DateUtils.SIMPLE_8_FORMATTER));
                    crmOrderDljzDetail.setEndDate(DateUtils.parseDate(dljzDetail.getDljzEndDate(), DateUtils.SIMPLE_8_FORMATTER));
                    this.crmOrderDljzDetailService.save(crmOrderDljzDetail);
                    break;
                case "BIZ_4":
                    GszcDetail gszcDetail = crmOrderBusinessRelDTO.getGszcDetail();
                    CrmOrderGszcDetail crmOrderGszcDetail = new CrmOrderGszcDetail();
                    crmOrderGszcDetail.setEventId(eventId);
                    crmOrderGszcDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
                    crmOrderGszcDetail.setAbsent(gszcDetail.getAbsent());
                    crmOrderGszcDetail.setRegLocationType(gszcDetail.getRegLocationType());
                    this.crmOrderGszcDetailService.save(crmOrderGszcDetail);
                    break;
            }
        } catch (Exception e) {
            log.error("CrmCustomOrderBusinessRelServiceImpl-->saveOrderBusiness: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void rollBackOrderBusiness(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        log.info("rollback new business {},eventId: {}", crmOrderBusinessRelDTO.getBusinessId(), eventId);
        this.removeById(eventId);
        switch (crmOrderBusinessRelDTO.getBusinessId()) {
            case "BIZ_1":
                this.crmOrderGjjsbdjDetailService.removeById(eventId);
                break;
            case "BIZ_2":
                this.crmOrderGjjsbdjDetailService.removeById(eventId);
                break;
            case "BIZ_3":
                this.crmOrderDljzDetailService.removeById(eventId);
                break;
            case "BIZ_4":
                this.crmOrderGszcDetailService.removeById(eventId);
                break;
        }
    }

    @Override
    public IPage getCrmOrderBusinessPage(Page page, CustomOrderBizQueryDTO customCompanyOrderQueryDTO) {
        return this.baseMapper.selectCrmOrderBusinessPage(page, customCompanyOrderQueryDTO);
    }

    private void cvtCrmOrderGjjsbdjDetail(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        GjjsbdjDetail detail = crmOrderBusinessRelDTO.getGjjsbdjDetail();
        CrmOrderGjjsbdjDetail crmOrderGjjsbdjDetail = new CrmOrderGjjsbdjDetail();
        crmOrderGjjsbdjDetail.setEventId(eventId);
        crmOrderGjjsbdjDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
        crmOrderGjjsbdjDetail.setBusinessId(crmOrderBusinessRelDTO.getBusinessId());
        crmOrderGjjsbdjDetail.setBeginDate(DateUtils.parseDate(detail.getGjjsbdjBeginDate(), DateUtils.SIMPLE_8_FORMATTER));
        crmOrderGjjsbdjDetail.setEndDate(DateUtils.parseDate(detail.getGjjsbdjBeginDate(), DateUtils.SIMPLE_8_FORMATTER));
        crmOrderGjjsbdjDetail.setEmployeeNum(detail.getEmployeeNum());
        this.crmOrderGjjsbdjDetailService.save(crmOrderGjjsbdjDetail);
    }
}
