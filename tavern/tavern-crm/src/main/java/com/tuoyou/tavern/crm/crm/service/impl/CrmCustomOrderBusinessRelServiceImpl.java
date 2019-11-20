package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.crm.dao.CrmOrderBusinessRelMapper;
import com.tuoyou.tavern.crm.crm.service.*;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowEventService;
import com.tuoyou.tavern.protocol.crm.dto.*;
import com.tuoyou.tavern.protocol.crm.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private CrmCompanyBusinessInfoService crmCompanyBusinessInfoService;


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

    @Override
    public void updateOrderBusinessAndStartWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO) {
        this.crmCustomOrderBusinessRelService.updateOrderBusiness(crmOrderBusinessRelDTO, crmOrderBusinessRelDTO.getEventId());
        this.workFlowEventService.updateWorkFlow(crmOrderBusinessRelDTO, crmOrderBusinessRelDTO.getEventId());
    }

    @Override
    public void deleteOrderBusinessAndWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        this.crmCustomOrderBusinessRelService.rollBackOrderBusiness(crmOrderBusinessRelDTO, eventId);
        this.workFlowEventService.removeWorkFlow(crmOrderBusinessRelDTO, eventId);
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
            CrmCompanyBusiness crmCompanyBusiness = new CrmCompanyBusiness();
            crmCompanyBusiness.setBusinessId(crmOrderBusinessRelDTO.getBusinessId());
            crmCompanyBusiness.setCompanyId(crmOrderBusinessRelDTO.getCompanyId());
            crmCompanyBusiness.setIsValid("1");
            crmCompanyBusiness.setUpdateDate(LocalDateTime.now());
            log.info("add new business {}", crmOrderBusinessRelDTO.getBusinessId());
            switch (crmOrderBusinessRelDTO.getBusinessId()) {
                case "BIZ_1":
                    this.cvtCrmOrderGjjsbdjDetail(crmOrderBusinessRelDTO, eventId, crmCompanyBusiness);
                    break;
                case "BIZ_2":
                    this.cvtCrmOrderGjjsbdjDetail(crmOrderBusinessRelDTO, eventId, crmCompanyBusiness);
                    break;
                case "BIZ_3":
                    CrmOrderDljzDetail crmOrderDljzDetail = new CrmOrderDljzDetail();
                    DljzDetail dljzDetail = crmOrderBusinessRelDTO.getDljzDetail();
                    crmOrderDljzDetail.setEventId(eventId);
                    crmOrderDljzDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
                    crmOrderDljzDetail.setIsBegin(dljzDetail.getIsBegin());
                    crmOrderDljzDetail.setDiff(dljzDetail.getDiff());
                    if(StringUtils.isNoneEmpty(dljzDetail.getDljzBeginDate())){
                        crmOrderDljzDetail.setBeginDate(DateUtils.parseDate(dljzDetail.getDljzBeginDate(), DateUtils.SIMPLE_8_FORMATTER));
                        crmCompanyBusiness.setBeginDate(DateUtils.parseDateTime(dljzDetail.getDljzBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                    }
                    if(StringUtils.isNoneEmpty(dljzDetail.getDljzEndDate())){
                        crmOrderDljzDetail.setEndDate(DateUtils.parseDate(StringUtils.replacePattern(dljzDetail.getDljzEndDate(), "/", ""), DateUtils.SIMPLE_8_FORMATTER));
                        crmCompanyBusiness.setEndDate(DateUtils.parseDateTime(StringUtils.replacePattern(dljzDetail.getDljzEndDate(), "/", "") + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                    }
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
            if (StringUtils.isNoneEmpty(crmOrderBusinessRelDTO.getCompanyId()))
                this.crmCompanyBusinessInfoService.save(crmCompanyBusiness);
        } catch (Exception e) {
            log.error("CrmCustomOrderBusinessRelServiceImpl-->saveOrderBusiness: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public void updateOrderBusiness(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId) {
        try {
            //录入订单
            CrmOrderBusinessRel crmOrderBusinessRel = new CrmOrderBusinessRel();
            BeanUtils.copyProperties(crmOrderBusinessRelDTO, crmOrderBusinessRel);
            crmOrderBusinessRel.setEventId(eventId);
            crmOrderBusinessRel.setCreateDate(LocalDateTime.now());
            this.updateById(crmOrderBusinessRel);
            CrmCompanyBusiness crmCompanyBusiness = new CrmCompanyBusiness();
            crmCompanyBusiness.setBusinessId(crmOrderBusinessRelDTO.getBusinessId());
            crmCompanyBusiness.setCompanyId(crmOrderBusinessRelDTO.getCompanyId());
            crmCompanyBusiness.setIsValid("1");
            crmCompanyBusiness.setUpdateDate(LocalDateTime.now());
            log.info("update  business {}", crmOrderBusinessRelDTO.getBusinessId());
            switch (crmOrderBusinessRelDTO.getBusinessId()) {
                case "BIZ_1":
                    this.cvtUpdateCrmOrderGjjsbdjDetail(crmOrderBusinessRelDTO, eventId, crmCompanyBusiness);
                    break;
                case "BIZ_2":
                    this.cvtUpdateCrmOrderGjjsbdjDetail(crmOrderBusinessRelDTO, eventId, crmCompanyBusiness);
                    break;
                case "BIZ_3":
                    CrmOrderDljzDetail crmOrderDljzDetail = new CrmOrderDljzDetail();
                    DljzDetail dljzDetail = crmOrderBusinessRelDTO.getDljzDetail();
                    crmOrderDljzDetail.setEventId(eventId);
                    crmOrderDljzDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
                    crmOrderDljzDetail.setIsBegin(dljzDetail.getIsBegin());
                    if(StringUtils.isNoneEmpty(dljzDetail.getDljzBeginDate())){
                        crmOrderDljzDetail.setBeginDate(DateUtils.parseDate(dljzDetail.getDljzBeginDate(), DateUtils.SIMPLE_8_FORMATTER));
                    }
                    if(StringUtils.isNoneEmpty(dljzDetail.getDljzEndDate())){
                        crmOrderDljzDetail.setEndDate(DateUtils.parseDate(StringUtils.replacePattern(dljzDetail.getDljzEndDate(), "/", ""), DateUtils.SIMPLE_8_FORMATTER));
                    }
                    crmOrderDljzDetail.setDiff(crmOrderBusinessRelDTO.getDljzDetail().getDiff());
                    this.crmOrderDljzDetailService.saveOrUpdate(crmOrderDljzDetail);
                    if(StringUtils.isNoneEmpty(dljzDetail.getDljzBeginDate())){
                        crmCompanyBusiness.setBeginDate(DateUtils.parseDateTime(dljzDetail.getDljzBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                    }
                    if(StringUtils.isNoneEmpty(dljzDetail.getDljzEndDate())){
                        crmCompanyBusiness.setEndDate(DateUtils.parseDateTime(StringUtils.replacePattern(dljzDetail.getDljzEndDate(), "/", "") + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
                    }
                    break;
                case "BIZ_4":
                    GszcDetail gszcDetail = crmOrderBusinessRelDTO.getGszcDetail();
                    CrmOrderGszcDetail crmOrderGszcDetail = new CrmOrderGszcDetail();
                    crmOrderGszcDetail.setEventId(eventId);
                    crmOrderGszcDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
                    crmOrderGszcDetail.setAbsent(gszcDetail.getAbsent());
                    crmOrderGszcDetail.setRegLocationType(gszcDetail.getRegLocationType());
                    this.crmOrderGszcDetailService.saveOrUpdate(crmOrderGszcDetail);
                    break;
            }
            if (StringUtils.isNoneEmpty(crmOrderBusinessRelDTO.getCompanyId()))
                this.crmCompanyBusinessInfoService.updateById(crmCompanyBusiness);
        } catch (Exception e) {
            log.error("CrmCustomOrderBusinessRelServiceImpl-->updateOrderBusiness: {}", e.getMessage());
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
        this.crmCompanyBusinessInfoService.remove(Wrappers.<CrmCompanyBusiness>query().lambda().eq(CrmCompanyBusiness::getBusinessId, crmOrderBusinessRelDTO.getBusinessId())
                .eq(CrmCompanyBusiness::getCompanyId, crmOrderBusinessRelDTO.getCompanyId()));
    }

    @Override
    public IPage getCrmOrderBusinessPage(Page page, CustomOrderBizQueryDTO customCompanyOrderQueryDTO) {
        return this.baseMapper.selectCrmOrderBusinessPage(page, customCompanyOrderQueryDTO);
    }

    @Override
    public CrmCompanyOrderDetailVO getCrmCompanyOrderDetail(String eventId, String companyId) {
        return this.baseMapper.selectCrmCompanyOrderDetail(eventId, companyId);
    }

    @Override
    public void updateThirdPartyInfo(CrmOrderBusinessRel crmOrderBusinessRel) {

    }

    private void cvtCrmOrderGjjsbdjDetail(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId, CrmCompanyBusiness crmCompanyBusiness) {
        GjjsbdjDetail detail = crmOrderBusinessRelDTO.getGjjsbdjDetail();
        CrmOrderGjjsbdjDetail crmOrderGjjsbdjDetail = new CrmOrderGjjsbdjDetail();
        crmOrderGjjsbdjDetail.setEventId(eventId);
        crmOrderGjjsbdjDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
        crmOrderGjjsbdjDetail.setBusinessId(crmOrderBusinessRelDTO.getBusinessId());
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjBeginDate())){
            crmOrderGjjsbdjDetail.setBeginDate(DateUtils.parseDate(detail.getGjjsbdjBeginDate(), DateUtils.SIMPLE_8_FORMATTER));
        }
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjEndDate())){
            String endDate = StringUtils.replacePattern(detail.getGjjsbdjEndDate(), "-", "");
            endDate = StringUtils.replacePattern(endDate, "/", "");
            crmOrderGjjsbdjDetail.setEndDate(DateUtils.parseDate(endDate, DateUtils.SIMPLE_8_FORMATTER));
        }
        crmOrderGjjsbdjDetail.setEmployeeNum(detail.getEmployeeNum());
        crmOrderGjjsbdjDetail.setDiff(crmOrderBusinessRelDTO.getGjjsbdjDetail().getDiff());
        this.crmOrderGjjsbdjDetailService.save(crmOrderGjjsbdjDetail);
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjBeginDate())){
            crmCompanyBusiness.setBeginDate(DateUtils.parseDateTime(detail.getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
        }
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjEndDate())){
            crmCompanyBusiness.setEndDate(DateUtils.parseDateTime(StringUtils.replacePattern(detail.getGjjsbdjEndDate(), "/", "") + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
        }
    }

    private void cvtUpdateCrmOrderGjjsbdjDetail(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId, CrmCompanyBusiness crmCompanyBusiness) {
        GjjsbdjDetail detail = crmOrderBusinessRelDTO.getGjjsbdjDetail();
        CrmOrderGjjsbdjDetail crmOrderGjjsbdjDetail = new CrmOrderGjjsbdjDetail();
        crmOrderGjjsbdjDetail.setEventId(eventId);
        crmOrderGjjsbdjDetail.setOrderId(crmOrderBusinessRelDTO.getOrderId());
        crmOrderGjjsbdjDetail.setBusinessId(crmOrderBusinessRelDTO.getBusinessId());
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjBeginDate())){
            crmOrderGjjsbdjDetail.setBeginDate(DateUtils.parseDate(detail.getGjjsbdjBeginDate(), DateUtils.SIMPLE_8_FORMATTER));
        }
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjEndDate())){
            String endDate = StringUtils.replacePattern(detail.getGjjsbdjEndDate(), "-", "");
            endDate = StringUtils.replacePattern(endDate, "/", "");
            crmOrderGjjsbdjDetail.setEndDate(DateUtils.parseDate(endDate, DateUtils.SIMPLE_8_FORMATTER));
        }
        crmOrderGjjsbdjDetail.setEmployeeNum(detail.getEmployeeNum());
        crmOrderGjjsbdjDetail.setDiff(crmOrderBusinessRelDTO.getGjjsbdjDetail().getDiff());
        this.crmOrderGjjsbdjDetailService.saveOrUpdate(crmOrderGjjsbdjDetail);
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjBeginDate())){
            crmCompanyBusiness.setBeginDate(DateUtils.parseDateTime(detail.getGjjsbdjBeginDate() + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
        }
        if(StringUtils.isNoneEmpty(detail.getGjjsbdjEndDate())){
            crmCompanyBusiness.setEndDate(DateUtils.parseDateTime(StringUtils.replacePattern(detail.getGjjsbdjEndDate(), "/", "") + " 00:00:00", DateUtils.SIMPLE_DATETIME_FORMATTER));
        }
    }
}
