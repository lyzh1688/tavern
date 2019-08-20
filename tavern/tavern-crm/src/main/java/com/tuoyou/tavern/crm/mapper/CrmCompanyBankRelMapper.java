package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmCompanyBankRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmCompanyBankRelMapper {
    int deleteByPrimaryKey(@Param("companyId") String companyId, @Param("bankId") String bankId);

    int insert(CrmCompanyBankRel record);

    List<CrmCompanyBankRel> selectAll();
}