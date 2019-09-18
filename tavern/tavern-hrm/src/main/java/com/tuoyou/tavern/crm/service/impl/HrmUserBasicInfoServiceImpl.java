package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.crm.dao.HrmUserBasicInfoMapper;
import com.tuoyou.tavern.crm.service.HrmUserBasicInfoService;
import com.tuoyou.tavern.crm.service.HrmUserRoleRelService;
import com.tuoyou.tavern.protocol.hrm.dto.StaffInfoDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserRoleRel;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserRoleRelVO;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        if (StringUtils.isEmpty(staffBasicInfo.getUserId())) {
            hrmUserBasicInfo.setUserId(UUIDUtil.randomUUID32());
        }
        hrmUserBasicInfo.setIsValid("1");
        hrmUserBasicInfo.setUpdateDate(LocalDateTime.now());
        this.saveOrUpdate(hrmUserBasicInfo);
        if (staffBasicInfo.getUserRoles().size() != 0) {
            this.hrmUserRoleRelService.removeByUserId(staffBasicInfo.getUserId());
        }
        List<HrmUserRoleRel> hrmUserRoleRelList = staffBasicInfo.getUserRoles().stream().map(role -> {
            HrmUserRoleRel hrmUserRoleRel = new HrmUserRoleRel();
            BeanUtils.copyProperties(role, hrmUserRoleRel);
            hrmUserRoleRel.setUpdateDate(LocalDateTime.now());
            return hrmUserRoleRel;
        }).collect(Collectors.toList());
        if (hrmUserRoleRelList.size() != 0)
            this.hrmUserRoleRelService.saveOrUpdateBatch(hrmUserRoleRelList);
    }

    @Override
    public IPage<StaffBasicInfo> page(Page page, StaffInfoDTO staffInfoDTO) {
        IPage<StaffBasicInfo> infoIPage = this.baseMapper.selectStaffPage(page, staffInfoDTO);
        List<StaffBasicInfo> staffBasicInfoList = infoIPage.getRecords().stream()
                .peek(info -> {
                    List<String> roleName = info.getUserRoles().stream().map(HrmUserRoleRelVO::getRoleName).collect(Collectors.toList());
                    info.setRoleNames(StringUtils.join(roleName, ","));
                }).collect(Collectors.toList());
        infoIPage.setRecords(staffBasicInfoList);

        return infoIPage;
    }

    @Override
    public List<HrmUserBasicInfo> getStaffByRole(String roleId) {
        return this.baseMapper.selectStaffByRole(roleId);
    }
}
