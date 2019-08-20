package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmCustomCompanyRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmCustomCompanyRelMapper {
    int deleteByPrimaryKey(@Param("customId") String customId, @Param("companyId") String companyId);

    int insert(CrmCustomCompanyRel record);

    List<CrmCustomCompanyRel> selectAll();
}