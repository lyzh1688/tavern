package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.dao.CrmCompanyStaffInfoMapper;
import com.tuoyou.tavern.crm.service.CrmCustomStaffInfoService;
import com.tuoyou.tavern.crm.service.CrmCustomStaffRelService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyStaffInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomStaffQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyInfoVO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffRel;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomStaffInfoServiceImpl extends ServiceImpl<CrmCompanyStaffInfoMapper, CrmCompanyStaffInfo> implements CrmCustomStaffInfoService {

    private CrmCustomStaffRelService crmCustomStaffRelService;

    @Transactional
    @Override
    public void createCrmCompanyStaffInfo(CrmCompanyStaffInfoDTO crmCompanyStaffInfoDTO) {
        String staffId = StringUtils.isEmpty(crmCompanyStaffInfoDTO.getStaffId()) ? UUIDUtil.randomUUID32() : crmCompanyStaffInfoDTO.getStaffId();

        CrmCompanyStaffInfo crmCompanyStaffInfo = new CrmCompanyStaffInfo();
        BeanUtils.copyProperties(crmCompanyStaffInfoDTO, crmCompanyStaffInfo);
        crmCompanyStaffInfo.setStaffId(staffId);
        crmCompanyStaffInfo.setUpdateDate(LocalDateTime.now());
        this.saveOrUpdate(crmCompanyStaffInfo);
        CrmCompanyStaffRel crmCompanyStaffRel = new CrmCompanyStaffRel();
        BeanUtils.copyProperties(crmCompanyStaffInfoDTO, crmCompanyStaffRel);
        crmCompanyStaffRel.setStaffId(staffId);
        if(StringUtils.isEmpty(crmCompanyStaffInfoDTO.getStaffId())){
            this.crmCustomStaffRelService.save(crmCompanyStaffRel);
        }

    }

    @Override
    public IPage<CrmCompanyInfoVO> getCrmStaffPage(Page page, CustomStaffQueryDTO customCompanyQueryDTO) {
        return this.baseMapper.selectCrmStaffPage(page, customCompanyQueryDTO);
    }
}
