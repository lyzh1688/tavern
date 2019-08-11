package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.CrmCustomBasicInfoMapper;
import com.tuoyou.tavern.crm.service.CrmBankInfoService;
import com.tuoyou.tavern.crm.service.CrmCustomBankInfoService;
import com.tuoyou.tavern.crm.service.CrmCustomBasicInfoService;
import com.tuoyou.tavern.crm.service.CrmCustomFinanceInfoService;
import com.tuoyou.tavern.protocol.crm.dto.CustomInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@AllArgsConstructor
@Service
public class CrmCustomBasicInfoServiceImpl extends ServiceImpl<CrmCustomBasicInfoMapper, CrmCustomBasicInfo> implements CrmCustomBasicInfoService {

    private final CrmCustomBankInfoService crmCustomBankInfoService;
    private final CrmCustomFinanceInfoService crmCustomFinanceInfoService;
    private final CrmBankInfoService crmBankInfoService;


    @Transactional
    @Override
    public void createCustom(CustomInfoDTO customInfoDTO) {
        CrmCustomBasicInfo crmCustomBasicInfo = new CrmCustomBasicInfo();
        BeanUtils.copyProperties(customInfoDTO, crmCustomBasicInfo);
        crmCustomBasicInfo.setIsValid("1");
        crmCustomBasicInfo.setUpdateDate(LocalDateTime.now());
        CrmCustomBankInfo crmCustomBankInfo = new CrmCustomBankInfo();
        BeanUtils.copyProperties(customInfoDTO, crmCustomBankInfo);
        crmCustomBankInfo.setUpdateDate(LocalDateTime.now());
        CrmBankInfo crmBankInfo = new CrmBankInfo();
        BeanUtils.copyProperties(customInfoDTO, crmBankInfo);
        CrmCustomFinanceInfo crmCustomFinanceInfo = new CrmCustomFinanceInfo();
        BeanUtils.copyProperties(customInfoDTO, crmCustomFinanceInfo);
        crmCustomFinanceInfo.setUpdateDate(LocalDateTime.now());
        this.saveOrUpdate(crmCustomBasicInfo);
        this.crmCustomBankInfoService.saveOrUpdate(crmCustomBankInfo);
        this.crmBankInfoService.saveOrUpdate(crmBankInfo);
        this.crmCustomFinanceInfoService.saveOrUpdate(crmCustomFinanceInfo);
    }

    @Override
    public IPage<CustomBasicInfoVO> getBasicInfoPage(Page page, CustomQueryDTO customQueryDTO) {
        return this.baseMapper.selectBasicInfoPage(page, customQueryDTO);
    }

    @Override
    public CustomBasicInfoVO getBasicInfo(String accnt, String password) {
        return this.baseMapper.selectBasicInfo(accnt, password);
    }
}
