package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.hrm.dto.StaffRoleDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserRoleRel;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/10 <br>
 */
public interface HrmUserRoleService extends IService<HrmRoleInfo> {

    IPage<HrmRoleInfo> getStaffRolePage(Page page, StaffRoleDTO staffRoleDTO);

}
