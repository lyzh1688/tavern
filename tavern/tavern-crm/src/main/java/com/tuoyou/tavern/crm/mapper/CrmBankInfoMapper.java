package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmBankInfo;
import java.util.List;

public interface CrmBankInfoMapper {
    int deleteByPrimaryKey(String bankId);

    int insert(CrmBankInfo record);

    CrmBankInfo selectByPrimaryKey(String bankId);

    List<CrmBankInfo> selectAll();

    int updateByPrimaryKey(CrmBankInfo record);
}