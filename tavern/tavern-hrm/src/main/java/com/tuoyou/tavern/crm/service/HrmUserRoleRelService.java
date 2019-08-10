package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserRoleRel;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/10 <br>
 */
public interface HrmUserRoleRelService extends IService<HrmUserRoleRel> {

    int removeByUserId(String userId);

}
