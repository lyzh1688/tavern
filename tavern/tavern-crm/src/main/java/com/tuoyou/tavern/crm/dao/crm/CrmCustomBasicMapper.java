package com.tuoyou.tavern.crm.dao.crm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBasicInfo;
import com.tuoyou.tavern.protocol.crm.model.CustomBasicInfoVO;
import org.apache.ibatis.annotations.Param;

public interface CrmCustomBasicMapper extends BaseMapper<CrmCustomBasicInfo> {

    IPage<CustomBasicInfoVO> selectBasicInfoPage(Page page, @Param("query") CustomQueryDTO customQueryDTO);

    CustomBasicInfoVO selectBasicInfo(@Param("accnt") String accnt, @Param("password") String password);
}