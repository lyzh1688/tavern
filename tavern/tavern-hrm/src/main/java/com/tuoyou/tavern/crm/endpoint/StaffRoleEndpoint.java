package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.service.HrmUserRoleService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuRoleRel;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.constants.HrmUserConstant;
import com.tuoyou.tavern.protocol.hrm.dto.StaffRoleDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import com.tuoyou.tavern.protocol.hrm.response.StaffRolePageResponse;
import com.tuoyou.tavern.protocol.hrm.response.StaffRoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
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
    public StaffRolePageResponse page(Page page, StaffRoleDTO staffRoleDTO) {
        return new StaffRolePageResponse(this.hrmUserRoleService.getStaffRolePage(page,staffRoleDTO));
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

    /**
     * 角色菜单新增
     */
    @GetMapping("/saveRoleMenus")
    public TavernResponse saveRoleMenus(@RequestBody List<AuthMenuRoleRel> authMenuRoleRelList){
        //1.RPC到auth保存菜单
        return new TavernResponse();
    }


}
