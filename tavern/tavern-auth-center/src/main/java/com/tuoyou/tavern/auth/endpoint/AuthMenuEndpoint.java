package com.tuoyou.tavern.auth.endpoint;

import com.tuoyou.tavern.auth.service.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.dto.AuthMenuDTO;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuVO;
import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuResponse;
import com.tuoyou.tavern.protocol.common.TavernRequestAuthFields;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    public AuthMenuResponse getNavTree(@RequestHeader(value = TavernRequestAuthFields.ROLE_ID) String roles) {
        List<AuthMenuVO> authMenuList = this.authMenuService.getAuthMenuList(roles, 1);
        return new AuthMenuResponse(authMenuList);
    }

    @GetMapping("/findMenuTree")
    public AuthMenuResponse getMenuTree() {
        List<AuthMenuVO> authMenuList = this.authMenuService.getAuthMenuList(null, 0);
        return new AuthMenuResponse(authMenuList);
    }
}
