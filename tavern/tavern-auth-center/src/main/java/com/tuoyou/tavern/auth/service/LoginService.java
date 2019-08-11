package com.tuoyou.tavern.auth.service;

import com.tuoyou.tavern.protocol.authcenter.dto.LoginDTO;
import com.tuoyou.tavern.protocol.authcenter.reponse.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface LoginService {

    LoginResponse login(LoginDTO login);


}
