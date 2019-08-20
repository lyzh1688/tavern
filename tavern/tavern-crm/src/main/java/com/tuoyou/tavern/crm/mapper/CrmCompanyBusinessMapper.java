package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmCompanyBusiness;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmCompanyBusinessMapper {
    int deleteByPrimaryKey(@Param("companyId") String companyId, @Param("businessId") String businessId);

    int insert(CrmCompanyBusiness record);

    CrmCompanyBusiness selectByPrimaryKey(@Param("companyId") String companyId, @Param("businessId") String businessId);

    List<CrmCompanyBusiness> selectAll();

    int updateByPrimaryKey(CrmCompanyBusiness record);
}