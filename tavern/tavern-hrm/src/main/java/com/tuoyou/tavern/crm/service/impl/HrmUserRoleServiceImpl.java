package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.HrmRoleInfoMapper;
import com.tuoyou.tavern.crm.service.HrmUserRoleService;
import com.tuoyou.tavern.protocol.hrm.dto.StaffRoleDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@AllArgsConstructor
@Service
public class HrmUserRoleServiceImpl extends ServiceImpl<HrmRoleInfoMapper, HrmRoleInfo> implements HrmUserRoleService {
    @Override
    public IPage<HrmRoleInfo> getStaffRolePage(Page page, StaffRoleDTO staffRoleDTO) {
        return this.baseMapper.selectStaffRolePage(page, staffRoleDTO);
    }
}
