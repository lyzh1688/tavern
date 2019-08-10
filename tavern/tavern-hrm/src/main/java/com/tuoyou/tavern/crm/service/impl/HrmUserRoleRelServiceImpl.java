package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.HrmUserRoleRelMapper;
import com.tuoyou.tavern.crm.service.HrmUserRoleRelService;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserRoleRel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/10 <br>
 */
@AllArgsConstructor
@Service
public class HrmUserRoleRelServiceImpl extends ServiceImpl<HrmUserRoleRelMapper, HrmUserRoleRel> implements HrmUserRoleRelService {

    @Override
    public int removeByUserId(String userId) {
        return this.baseMapper.deleteByUserId(userId);
    }
}
