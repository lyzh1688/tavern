package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmCompanyInfo;
import java.util.List;

public interface CrmCompanyInfoMapper {
    int deleteByPrimaryKey(String companyId);

    int insert(CrmCompanyInfo record);

    CrmCompanyInfo selectByPrimaryKey(String companyId);

    List<CrmCompanyInfo> selectAll();

    int updateByPrimaryKey(CrmCompanyInfo record);
}