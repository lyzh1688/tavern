package com.tuoyou.tavern.auth.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tuoyou.tavern.auth.service.AuthMenuRoleRelService;
import com.tuoyou.tavern.auth.service.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.dto.AuthMenuDTO;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuRoleRel;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuVO;
import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuResponse;
import com.tuoyou.tavern.protocol.common.TavernRequestAuthFields;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.constants.HrmUserConstant;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class AuthMenuEndpoint {

    private final AuthMenuService authMenuService;
    private final AuthMenuRoleRelService authMenuRoleRelService;

    @PostMapping("/save")
    public TavernResponse save(@Valid @RequestBody AuthMenuDTO authMenuDTO) {
        AuthMenu authMenu = new AuthMenu();
        BeanUtils.copyProperties(authMenuDTO, authMenu);
        if (authMenu.getParentId() == null) {
            authMenu.setParentId(0L);
        }
        authMenu.setUpdateDate(LocalDateTime.now());
        if (authMenu.getMenuId() == null || authMenu.getMenuId() == 0) {
            this.authMenuService.save(authMenu);
            return new TavernResponse();
        }
        this.authMenuService.updateById(authMenu);
        return new TavernResponse();
    }

    @PostMapping("/delete")
    public TavernResponse delete(@Valid @RequestBody List<AuthMenuDTO> authMenu) {
        this.authMenuService.removeByIds(authMenu.stream().map(AuthMenuDTO::getMenuId).collect(Collectors.toList()));
        return new TavernResponse();
    }

    @GetMapping("/findNavTree")
    public AuthMenuResponse getNavTree(@RequestParam(name = "roles") String roles) {
        List<AuthMenuVO> authMenuList = this.authMenuService.getAuthMenuList(roles, 1);
        return new AuthMenuResponse(authMenuList);
    }

    @GetMapping("/findRoleMenus")
    public AuthMenuResponse getRoleMenus(@RequestParam(name = "roleId") String roleId) {
        return new AuthMenuResponse(this.authMenuService.getAuthMenuByRoles(roleId));
    }

    @GetMapping("/findMenuTree")
    public AuthMenuResponse getMenuTree() {
        List<AuthMenuVO> authMenuList = this.authMenuService.getAuthMenuList(null, 0);
        return new AuthMenuResponse(authMenuList);
    }

    @Transactional
    @PostMapping("/saveAuthRoleMenus")
    public TavernResponse saveMenuRole(@Valid @RequestBody List<AuthMenuRoleRel> authMenuRoleRelList) throws Exception {
        for (AuthMenuRoleRel authMenuRoleRel : authMenuRoleRelList) {
            authMenuRoleRel.setUpdateDate(LocalDateTime.now());
            if (HrmUserConstant.ADMIN.equalsIgnoreCase(authMenuRoleRel.getRoleId())) {
                throw new Exception("超级管理员拥有所有菜单权限，不允许修改!");
            }
        }
        this.authMenuRoleRelService.remove(Wrappers.<AuthMenuRoleRel>query().lambda().eq(AuthMenuRoleRel::getRoleId, authMenuRoleRelList.get(0).getRoleId()));
        //全部删除
        if (authMenuRoleRelList.size() == 1 && authMenuRoleRelList.get(0).getMenuId() == null) {
            return new TavernResponse();
        }
        this.authMenuRoleRelService.saveBatch(authMenuRoleRelList);
        return new TavernResponse();
    }
}
