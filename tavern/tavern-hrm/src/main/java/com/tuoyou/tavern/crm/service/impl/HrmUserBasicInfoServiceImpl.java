package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.HrmUserBasicInfoMapper;
import com.tuoyou.tavern.crm.service.HrmUserBasicInfoService;
import com.tuoyou.tavern.crm.service.HrmUserRoleRelService;
import com.tuoyou.tavern.protocol.hrm.dto.StaffInfoDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/08 <br>
 */
@AllArgsConstructor
@Service
public class HrmUserBasicInfoServiceImpl extends ServiceImpl<HrmUserBasicInfoMapper, HrmUserBasicInfo> implements HrmUserBasicInfoService {

    private final HrmUserRoleRelService hrmUserRoleRelService;

    @Override
    public StaffBasicInfo queryStaffBasicInfo(String accnt, String password) {
        return this.baseMapper.selectStaffBasicInfo(accnt, password);
    }

    @Transactional
    @Override
    public void saveStaffBasicInfo(StaffBasicInfo staffBasicInfo) {
        HrmUserBasicInfo hrmUserBasicInfo = new HrmUserBasicInfo();
        BeanUtils.copyProperties(staffBasicInfo, hrmUserBasicInfo);
        hrmUserBasicInfo.setIsValid("1");
        hrmUserBasicInfo.setUpdateDate(LocalDateTime.now());
        this.saveOrUpdate(hrmUserBasicInfo);
        if (staffBasicInfo.getRoleList().size() != 0) {
            this.hrmUserRoleRelService.removeByUserId(staffBasicInfo.getUserId());
        }
        staffBasicInfo.getRoleList().stream().peek(role -> role.setUpdateDate(LocalDateTime.now()));
        this.hrmUserRoleRelService.saveOrUpdateBatch(staffBasicInfo.getRoleList());
    }

    @Override
    public IPage<StaffBasicInfo> page(Page page, StaffInfoDTO staffInfoDTO) {
        return this.baseMapper.selectStaffPage(page, staffInfoDTO);
    }

    @Override
    public List<HrmUserBasicInfo> getStaffByRole(String roleId) {
        return this.baseMapper.selectStaffByRole(roleId);
    }
}
