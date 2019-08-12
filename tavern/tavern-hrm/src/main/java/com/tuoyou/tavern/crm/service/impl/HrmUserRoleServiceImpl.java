package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.HrmRoleInfoMapper;
import com.tuoyou.tavern.crm.service.HrmUserRoleService;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/8/12 <br>
 */
@AllArgsConstructor
@Service
public class HrmUserRoleServiceImpl extends ServiceImpl<HrmRoleInfoMapper, HrmRoleInfo> implements HrmUserRoleService {
}
