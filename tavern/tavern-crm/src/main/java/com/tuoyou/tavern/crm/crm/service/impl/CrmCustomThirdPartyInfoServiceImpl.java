package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.crm.dao.CrmThirdPartyInfoMapper;
import com.tuoyou.tavern.crm.crm.service.CrmCustomThirdPartyInfoService;
import com.tuoyou.tavern.protocol.crm.dto.CrmThirdPartyInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomThirdPartyQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmThirdPartyInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomThirdPartyInfoServiceImpl extends ServiceImpl<CrmThirdPartyInfoMapper, CrmThirdPartyInfo> implements CrmCustomThirdPartyInfoService {

    @Override
    public void createCrmCustomThirdPartyInfo(CrmThirdPartyInfoDTO crmThirdPartyInfoDTO) {
        String thridPartyId = StringUtils.isEmpty(crmThirdPartyInfoDTO.getThirdPartyId()) ? UUIDUtil.randomUUID32() : crmThirdPartyInfoDTO.getThirdPartyId();
        CrmThirdPartyInfo crmThirdPartyInfo = new CrmThirdPartyInfo();
        BeanUtils.copyProperties(crmThirdPartyInfoDTO, crmThirdPartyInfo);
        crmThirdPartyInfo.setThirdPartyId(thridPartyId);
        List<String> areaList = crmThirdPartyInfoDTO.getArea();
        if (areaList.size() == 1) {
            crmThirdPartyInfo.setProvince(areaList.get(0));
            crmThirdPartyInfo.setCity(areaList.get(0));
            crmThirdPartyInfo.setDistrict(areaList.get(0));
        }
        if (areaList.size() == 2) {
            crmThirdPartyInfo.setProvince(areaList.get(0));
            crmThirdPartyInfo.setCity(areaList.get(1));
            crmThirdPartyInfo.setDistrict(areaList.get(1));
        }
        if (areaList.size() == 3) {
            crmThirdPartyInfo.setProvince(areaList.get(0));
            crmThirdPartyInfo.setCity(areaList.get(1));
            crmThirdPartyInfo.setDistrict(areaList.get(2));
        }
        this.saveOrUpdate(crmThirdPartyInfo);
    }

    @Override
    public IPage<CrmThirdPartyInfo> queryCustomThirdPartyInfoPage(Page page, CustomThirdPartyQueryDTO customCompanyOrderQueryDTO) {
        return this.baseMapper.selectPage(page, customCompanyOrderQueryDTO);
    }
}
