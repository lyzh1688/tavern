package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.common.core.util.CommonUtils;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.dao.crm.CrmCompanyInfoMapper;
import com.tuoyou.tavern.crm.service.CrmCompanyBankRelService;
import com.tuoyou.tavern.crm.service.CrmCustomBankInfoService;
import com.tuoyou.tavern.crm.service.CrmCustomCompanyInfoService;
import com.tuoyou.tavern.crm.service.CrmCustomCompanyRelService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomCompanyInfoServiceImpl extends ServiceImpl<CrmCompanyInfoMapper, CrmCompanyInfo> implements CrmCustomCompanyInfoService {

    private CrmCustomCompanyRelService crmCustomCompanyRelService;
    private CrmCustomBankInfoService crmCustomBankInfoService;
    private CrmCompanyBankRelService crmCompanyBankRelService;


    @Transactional
    @Override
    public void createCrmCompanyInfo(CrmCompanyInfoDTO crmCompanyInfoDTO) {
        String companyId = StringUtils.isEmpty(crmCompanyInfoDTO.getCompanyId()) ? UUIDUtil.randomUUID32() : crmCompanyInfoDTO.getCompanyId();
        CrmCompanyInfo crmCompanyInfo = new CrmCompanyInfo();
        BeanUtils.copyProperties(crmCompanyInfoDTO, crmCompanyInfo);
        crmCompanyInfo.setUpdateDate(LocalDateTime.now());
        crmCompanyInfo.setTaxRate(StringUtils.isEmpty(crmCompanyInfoDTO.getTaxRate()) ? null : new BigDecimal(crmCompanyInfoDTO.getTaxRate()));
        crmCompanyInfo.setCompanyId(companyId);
        List<String> areaList = crmCompanyInfoDTO.getArea();
        if (areaList.size() == 1) {
            crmCompanyInfo.setProvince(areaList.get(0));
            crmCompanyInfo.setCity(areaList.get(0));
            crmCompanyInfo.setDistrict(areaList.get(0));
        }
        if (areaList.size() == 2) {
            crmCompanyInfo.setProvince(areaList.get(0));
            crmCompanyInfo.setCity(areaList.get(1));
            crmCompanyInfo.setDistrict(areaList.get(1));
        }
        if (areaList.size() == 3) {
            crmCompanyInfo.setProvince(areaList.get(0));
            crmCompanyInfo.setCity(areaList.get(1));
            crmCompanyInfo.setDistrict(areaList.get(2));
        }
        this.saveOrUpdate(crmCompanyInfo);
        if (StringUtils.isEmpty(crmCompanyInfoDTO.getCompanyId())) {
            CrmCustomCompanyRel crmCustomCompanyRel = new CrmCustomCompanyRel();
            crmCustomCompanyRel.setCompanyId(companyId);
            crmCustomCompanyRel.setCustomId(crmCompanyInfoDTO.getCustomId());
            this.crmCustomCompanyRelService.save(crmCustomCompanyRel);
        }
        //新增关联银行
        //1.先删后插入
        if (crmCompanyInfoDTO.getBanks().size() != 0) {

            Map<String, CrmBankInfo> crmBankInfoMap = this.crmCustomBankInfoService.list()
                    .parallelStream()
                    .collect(Collectors.toConcurrentMap(CrmBankInfo::getBankName, v -> v));

            //记录所有的银行
            List<CrmBankInfo> crmBankInfoList = crmCompanyInfoDTO.getBanks()
                    .stream()
                    .filter(bank -> !crmBankInfoMap.containsKey(bank))
                    .map(bank -> {
                        CrmBankInfo crmBankInfo = new CrmBankInfo();
                        crmBankInfo.setBankId(UUIDUtil.randomUUID32());
                        crmBankInfo.setBankName(bank);
                        crmBankInfoMap.put(bank, crmBankInfo);
                        return crmBankInfo;
                    }).collect(Collectors.toList());
            this.crmCustomBankInfoService.saveBatch(crmBankInfoList);

            List<CrmCompanyBankRel> crmCompanyBankRelList = crmCompanyInfoDTO.getBanks()
                    .stream()
                    .map(bank -> {
                        CrmCompanyBankRel crmCompanyBankRel = new CrmCompanyBankRel();
                        crmCompanyBankRel.setBankId(crmBankInfoMap.get(bank).getBankId());
                        crmCompanyBankRel.setCompanyId(companyId);
                        return crmCompanyBankRel;
                    }).collect(Collectors.toList());
            this.crmCompanyBankRelService.remove(Wrappers.<CrmCompanyBankRel>query().lambda().eq(CrmCompanyBankRel::getCompanyId, crmCompanyInfoDTO.getCompanyId()));
            this.crmCompanyBankRelService.saveBatch(crmCompanyBankRelList);
        }
    }


    @Override
    public IPage<CrmCompanyInfoVO> getCompanyInfoPage(Page page, CustomCompanyQueryDTO customCompanyQueryDTO) {
        IPage<CrmCompanyInfo> infoIPage = this.baseMapper.selectCompanyInfoPage(page, customCompanyQueryDTO);
        List<CrmCompanyInfoVO> crmCompanyInfoVOList = infoIPage.getRecords()
                .stream()
                .map(record -> {
                    CrmCompanyInfoVO crmCompanyInfoVO = new CrmCompanyInfoVO();
                    BeanUtils.copyProperties(record, crmCompanyInfoVO);
                    crmCompanyInfoVO.setCustomId(customCompanyQueryDTO.getCustomId());
                    crmCompanyInfoVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    List<String> banks = this.crmCustomBankInfoService.getCrmBankInfoByCompanyId(record.getCompanyId())
                            .parallelStream()
                            .map(CrmBankInfo::getBankName)
                            .collect(Collectors.toList());
                    crmCompanyInfoVO.setBanks(banks);
                    return crmCompanyInfoVO;
                }).collect(Collectors.toList());
        return CommonUtils.newIPage(infoIPage, crmCompanyInfoVOList);
    }

    @Override
    public List<CrmCompanyInfo> getCompanyByCustomId(String customId) {
        return this.baseMapper.selectCompanyByCustomId(customId);
    }
}
