package com.tuoyou.tavern.crm.mapper;

import com.tuoyou.tavern.crm.entity.CrmOrderBusinessRel;
import java.util.List;

public interface CrmOrderBusinessRelMapper {
    int deleteByPrimaryKey(String eventId);

    int insert(CrmOrderBusinessRel record);

    CrmOrderBusinessRel selectByPrimaryKey(String eventId);

    List<CrmOrderBusinessRel> selectAll();

    int updateByPrimaryKey(CrmOrderBusinessRel record);
}