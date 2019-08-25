package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmCompanyStaffRelMapper {
    int deleteByPrimaryKey(@Param("companyId") String companyId, @Param("staffId") String staffId);

    int insert(CrmCompanyStaffRel record);

    List<CrmCompanyStaffRel> selectAll();
}