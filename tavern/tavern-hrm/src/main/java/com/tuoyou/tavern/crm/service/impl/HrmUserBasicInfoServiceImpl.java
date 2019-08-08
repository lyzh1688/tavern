package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.HrmUserBasicInfoMapper;
import com.tuoyou.tavern.crm.service.HrmUserBasicInfoService;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/08 <br>
 */
@AllArgsConstructor
@Service
public class HrmUserBasicInfoServiceImpl extends ServiceImpl<HrmUserBasicInfoMapper, HrmUserBasicInfo> implements HrmUserBasicInfoService {

    @Override
    public StaffBasicInfo queryStaffBasicInfo(String accnt, String password) {
        return this.baseMapper.selectStaffBasicInfo(accnt, password);
    }
}
