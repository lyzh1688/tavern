package com.tuoyou.tavern.crm.dao;

import com.tuoyou.tavern.crm.entity.CrmCustomBankInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmCustomBankInfoMapper {
    int deleteByPrimaryKey(@Param("customId") String customId, @Param("bankId") String bankId);

    int insert(CrmCustomBankInfo record);

    CrmCustomBankInfo selectByPrimaryKey(@Param("customId") String customId, @Param("bankId") String bankId);

    List<CrmCustomBankInfo> selectAll();

    int updateByPrimaryKey(CrmCustomBankInfo record);
}