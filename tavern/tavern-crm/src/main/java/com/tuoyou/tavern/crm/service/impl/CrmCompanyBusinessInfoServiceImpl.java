package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.crm.CrmCompanyBusinessMapper;
import com.tuoyou.tavern.crm.service.CrmCompanyBusinessInfoService;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyBusinessQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusiness;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusinessVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class CrmCompanyBusinessInfoServiceImpl extends ServiceImpl<CrmCompanyBusinessMapper, CrmCompanyBusiness> implements CrmCompanyBusinessInfoService {


    @Override
    public IPage<CrmCompanyBusinessVO> getCompanyBusinessPage(Page page,  CustomCompanyBusinessQueryDTO queryDTO) {
        return this.baseMapper.selectCompanyBusinessPage(page, queryDTO);
    }

}
