package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffInfo;
import java.util.List;

public interface CrmCompanyStaffInfoMapper {
    int insert(CrmCompanyStaffInfo record);

    List<CrmCompanyStaffInfo> selectAll();
}