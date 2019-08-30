package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyBusinessQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusiness;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusinessVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
public interface CrmCompanyBusinessInfoService extends IService<CrmCompanyBusiness> {
    IPage<CrmCompanyBusinessVO> getCompanyBusinessPage(Page page, CustomCompanyBusinessQueryDTO queryDTO);

}
