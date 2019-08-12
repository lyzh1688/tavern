package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.service.HrmUserRoleService;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.constants.HrmUserConstant;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import com.tuoyou.tavern.protocol.hrm.response.StaffRolePageResponse;
import com.tuoyou.tavern.protocol.hrm.response.StaffRoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/8/12 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/hrm/role")
public class StaffRoleEndpoint {

    private final HrmUserRoleService hrmUserRoleService;

    /**
     * 新增修改角色
     */
    @PostMapping("/save")
    public TavernResponse save(@Valid @RequestBody HrmRoleInfo hrmRoleInfo) {

        HrmRoleInfo role = this.hrmUserRoleService.getById(hrmRoleInfo.getRoleId());
        if (!Objects.isNull(role)) {
            if (HrmUserConstant.ADMIN.equals(role.getRoleName().toLowerCase())) {
                return new TavernResponse(RetCode.SYS_ERROR, "超级管理员不能修改！");
            }
        }
        this.hrmUserRoleService.saveOrUpdate(hrmRoleInfo);
        return new TavernResponse();
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/delete")
    public TavernResponse delete(@Valid @RequestBody HrmRoleInfo hrmRoleInfo) {

        HrmRoleInfo role = this.hrmUserRoleService.getById(hrmRoleInfo.getRoleId());
        if (!Objects.isNull(role)) {
            if (HrmUserConstant.ADMIN.equals(role.getRoleName().toLowerCase())) {
                return new TavernResponse(RetCode.SYS_ERROR, "超级管理员不能删除！");
            }
        }
        this.hrmUserRoleService.removeById(hrmRoleInfo.getRoleId());
        return new TavernResponse();
    }

    /**
     * 分页查询角色
     */
    @GetMapping("/page")
    public StaffRolePageResponse page(Page<HrmRoleInfo> page) {
        return new StaffRolePageResponse(this.hrmUserRoleService.page(page));
    }

    /**
     * 查询所有角色
     */
    @GetMapping("/all")
    public StaffRoleResponse getAll(){
        return new StaffRoleResponse(this.hrmUserRoleService.list());
    }

    /**
     * 查询角色菜单
     */
    @GetMapping("/findRoleMenus")
    public TavernResponse findRoleMenus(@RequestParam String roleId){
        //1.RPC到auth获取菜单
        return new TavernResponse();
    }



}
