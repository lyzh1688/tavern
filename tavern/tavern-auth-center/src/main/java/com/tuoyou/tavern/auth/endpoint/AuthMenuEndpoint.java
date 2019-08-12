package com.tuoyou.tavern.auth.endpoint;

import com.tuoyou.tavern.auth.service.AuthMenuService;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.reponse.AuthMenuResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public TavernResponse save(@Valid @RequestBody AuthMenu authMenu) {
        this.authMenuService.saveOrUpdate(authMenu);
        return new TavernResponse();
    }

    @PostMapping("/delete")
    public TavernResponse delete(@Valid @RequestBody List<AuthMenu> authMenu) {
        this.authMenuService.removeByIds(authMenu.stream().map(AuthMenu::getMenuId).collect(Collectors.toList()));
        return new TavernResponse();
    }

    @GetMapping("/findNavTree")
    public AuthMenuResponse getNavTree(@RequestParam String userAccnt) {
        List<AuthMenu> authMenuList = this.authMenuService.getAuthMenuList(userAccnt, "1");
        return new AuthMenuResponse(authMenuList);
    }

    @GetMapping("/findMenuTree")
    public AuthMenuResponse getMenuTree() {
        List<AuthMenu> authMenuList = this.authMenuService.getAuthMenuList(null, "1");
        return new AuthMenuResponse(authMenuList);
    }
}
