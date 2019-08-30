package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBasicInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomCompanyRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmCustomCompanyRelMapper extends BaseMapper<CrmCustomCompanyRel> {

    int deleteByPrimaryKey(String customId,String companyId);
}