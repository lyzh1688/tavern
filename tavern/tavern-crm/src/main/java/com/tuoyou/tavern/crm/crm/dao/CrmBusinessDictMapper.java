package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmBusinessDictMapper extends BaseMapper<CrmBusinessDict> {
    List<CrmBusinessDict> selectCrmOrderBusinessByOrderId(@Param("orderId") String orderId);
}