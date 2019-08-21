package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CustomInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBasicInfo;
import com.tuoyou.tavern.protocol.crm.model.CustomBasicInfoVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CrmCustomBasicInfoService extends IService<CrmCustomBasicInfo> {

    void createCustom(CustomInfoDTO customInfoDTO);

    IPage<CustomBasicInfoVO> getBasicInfoPage(Page page, CustomQueryDTO customQueryDTO);

    CustomBasicInfoVO getBasicInfo(String accnt, String password);
}
