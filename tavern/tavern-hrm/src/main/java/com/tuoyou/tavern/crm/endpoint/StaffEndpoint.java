package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.auth.libs.utils.PwdUtils;
import com.tuoyou.tavern.crm.service.HrmUserBasicInfoService;
import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuPermissionResponse;
import com.tuoyou.tavern.protocol.authcenter.spi.AuthMenuService;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.constants.HrmUserConstant;
import com.tuoyou.tavern.protocol.hrm.dto.StaffInfoDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserRoleRelVO;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import com.tuoyou.tavern.protocol.hrm.response.StaffInfoPageResponse;
import com.tuoyou.tavern.protocol.hrm.response.StaffInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class StaffEndpoint {
    private HrmUserBasicInfoService hrmUserBasicInfoService;
    private AuthMenuService authMenuService;

    /*
     * 创建用户
     * */
    @PostMapping("/save")
    public TavernResponse createStaff(@RequestBody StaffBasicInfo staffBasicInfo) {
        HrmUserBasicInfo hrmUserBasicInfo = this.hrmUserBasicInfoService.getById(staffBasicInfo.getUserId());
        if (hrmUserBasicInfo != null) {
            //修改
            if (staffBasicInfo.getUserName().equalsIgnoreCase(HrmUserConstant.ADMIN)) {
                return new TavernResponse(RetCode.SYS_ERROR, "系统管理员不允许修改");
            }
        }
        if (staffBasicInfo.getPassword() != null) {
            String salt = PwdUtils.getSalt();
            if (hrmUserBasicInfo == null) {
                // 新增用户
                HrmUserBasicInfo newInfo = this.hrmUserBasicInfoService.getOne(Wrappers.<HrmUserBasicInfo>query().lambda()
                        .eq(HrmUserBasicInfo::getUserAccnt, staffBasicInfo.getUserAccnt()));
                if (newInfo != null) {
                    return new TavernResponse(RetCode.SYS_ERROR, "用户名已存在");
                }
                String password = PwdUtils.encode(staffBasicInfo.getPassword(), salt);
                staffBasicInfo.setSalt(salt);
                staffBasicInfo.setPassword(password);
            } else {
                // 修改用户, 且修改了密码
                if (!staffBasicInfo.getPassword().equals(hrmUserBasicInfo.getPassword())) {
                    String password = PwdUtils.encode(staffBasicInfo.getPassword(), salt);
                    staffBasicInfo.setSalt(salt);
                    staffBasicInfo.setPassword(password);
                }
            }
        }
        this.hrmUserBasicInfoService.saveStaffBasicInfo(staffBasicInfo);
        return new TavernResponse();
    }

    /*
     * 查询用户基本信息
     * */
    //RESOURCE-PATH:/hrm/staff?accnt=${accnt}&password=${password}
    @GetMapping("/staff")
    public StaffInfoResponse queryStaffBasicInfo(@RequestParam(name = "accnt") String accnt, @RequestParam(name = "password") String password) {
        StaffBasicInfo staffBasicInfo = this.hrmUserBasicInfoService.queryStaffBasicInfo(accnt, password);
        StaffInfoResponse staffInfoResponse = new StaffInfoResponse();
        // 账号不存在、密码错误
        if (Objects.isNull(staffBasicInfo)) {
            staffInfoResponse.setRetCode(RetCode.AUTH_FAILED);
            staffInfoResponse.setRetMessage("用户不存在");
            return staffInfoResponse;
        }
        if (StringUtils.isEmpty(staffBasicInfo.getSalt()) && password.equals(staffBasicInfo.getPassword())) {
            staffInfoResponse.setData(staffBasicInfo);
            return staffInfoResponse;
        }

        if (!PwdUtils.matches(staffBasicInfo.getSalt(), password, staffBasicInfo.getPassword())) {
            staffInfoResponse.setRetCode(RetCode.AUTH_FAILED);
            staffInfoResponse.setRetMessage("密码不正确");
            return staffInfoResponse;
        }

        staffInfoResponse.setData(staffBasicInfo);
        return staffInfoResponse;
    }

    /*
     * 查询用户
     *
     * */
    @GetMapping("/page")
    public StaffInfoPageResponse queryStaffInfo(Page page, StaffInfoDTO staffInfoDTO) {
        return new StaffInfoPageResponse(this.hrmUserBasicInfoService.page(page, staffInfoDTO));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete")
    public TavernResponse deleteStaff(@RequestBody List<String> userList) {
        for (String userId : userList) {
            HrmUserBasicInfo hrmUserBasicInfo = this.hrmUserBasicInfoService.getOne(Wrappers.<HrmUserBasicInfo>query().lambda()
                    .eq(HrmUserBasicInfo::getUserId, userId));
            if (hrmUserBasicInfo != null && hrmUserBasicInfo.getUserAccnt().equalsIgnoreCase(HrmUserConstant.ADMIN)) {
                return new TavernResponse(RetCode.SYS_ERROR, "系统管理员不允许删除");
            }
        }
        if (!userList.isEmpty()) {
            List<HrmUserBasicInfo> hrmUserBasicInfoList = userList
                    .stream()
                    .map(info -> {
                        HrmUserBasicInfo tmpInfo = new HrmUserBasicInfo();
                        tmpInfo.setUserId(info);
                        tmpInfo.setIsValid("0");
                        tmpInfo.setUpdateDate(LocalDateTime.now());
                        return tmpInfo;
                    }).collect(Collectors.toList());
            this.hrmUserBasicInfoService.updateBatchById(hrmUserBasicInfoList);
        }
        return new TavernResponse();
    }

    @GetMapping(value = "/findPermissions")
    public AuthMenuPermissionResponse getAuthMenuPermission(@RequestParam(name = "userAccnt") String userAccnt) {
        StaffBasicInfo staffBasicInfo = this.hrmUserBasicInfoService.queryStaffBasicInfo(userAccnt, null);
        String roleId = org.apache.commons.lang3.StringUtils.join(staffBasicInfo.getUserRoles().parallelStream().map(HrmUserRoleRelVO::getRoleId).collect(Collectors.toList()), ",");
        return this.authMenuService.queryAuthMenuPermissionByRole(roleId);
    }

}
