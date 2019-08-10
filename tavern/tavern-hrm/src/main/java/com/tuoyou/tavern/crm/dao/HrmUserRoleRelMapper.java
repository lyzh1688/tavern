package com.tuoyou.tavern.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserRoleRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrmUserRoleRelMapper extends BaseMapper<HrmUserRoleRel> {
    int deleteByUserId(String userId);
}