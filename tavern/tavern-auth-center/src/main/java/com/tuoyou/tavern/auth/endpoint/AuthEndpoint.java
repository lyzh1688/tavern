package com.tuoyou.tavern.auth.endpoint;

import com.tuoyou.tavern.auth.service.LoginService;
import com.tuoyou.tavern.protocol.authcenter.dto.LoginDTO;
import com.tuoyou.tavern.protocol.authcenter.reponse.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
@RestController
public class AuthEndpoint {

    @Autowired
    private LoginService loginService;

    @PostMapping(name = "/login")
    public LoginResponse login(@Valid @RequestBody LoginDTO login) {
        return loginService.login(login);
    }
}
