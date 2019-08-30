package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.common.core.util.CommonUtils;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.crm.service.CrmCustomBankInfoService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomBasicInfoService;
import com.tuoyou.tavern.crm.crm.dao.CrmCustomBasicInfoMapper;
import com.tuoyou.tavern.protocol.crm.dto.CustomInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBasicInfo;
import com.tuoyou.tavern.protocol.crm.model.CustomBasicInfoVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@AllArgsConstructor
@Service
public class CrmCustomBasicInfoServiceImpl extends ServiceImpl<CrmCustomBasicInfoMapper, CrmCustomBasicInfo> implements CrmCustomBasicInfoService {

    private final CrmCustomBankInfoService crmCustomBankInfoService;
    private final CrmCustomBankInfoService crmBankInfoService;


    @Override
    public void createCustom(CustomInfoDTO customInfoDTO) {
        CrmCustomBasicInfo crmCustomBasicInfo = new CrmCustomBasicInfo();
        BeanUtils.copyProperties(customInfoDTO, crmCustomBasicInfo);
        crmCustomBasicInfo.setCustomId(StringUtils.isEmpty(customInfoDTO.getCustomId()) ? UUIDUtil.randomUUID32() : customInfoDTO.getCustomId());
        crmCustomBasicInfo.setIsValid("1");
        crmCustomBasicInfo.setUpdateDate(LocalDateTime.now());
        this.saveOrUpdate(crmCustomBasicInfo);
    }

    @Override
    public IPage<CustomBasicInfoVO> getBasicInfoPage(Page page, CustomQueryDTO customQueryDTO) {
        IPage<CrmCustomBasicInfo> infoIPage = this.baseMapper.selectBasicInfoPage(page, customQueryDTO);
        List<CustomBasicInfoVO> customBasicInfoVOList = infoIPage.getRecords()
                .stream()
                .map(record -> {
                    CustomBasicInfoVO customBasicInfoVO = new CustomBasicInfoVO();
                    BeanUtils.copyProperties(record, customBasicInfoVO);
                    customBasicInfoVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return customBasicInfoVO;
                }).collect(Collectors.toList());
        return CommonUtils.newIPage(infoIPage, customBasicInfoVOList);
    }

}
