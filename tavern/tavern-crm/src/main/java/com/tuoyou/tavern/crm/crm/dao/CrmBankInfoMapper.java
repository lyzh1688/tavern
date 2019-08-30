package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.crm.model.CrmBankInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmBankInfoMapper extends BaseMapper<CrmBankInfo> {

    List<CrmBankInfo> selectCrmBankInfoByCompanyId(@Param("companyId") String companyId);
}