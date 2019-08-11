package com.tuoyou.tavern.protocol.authcenter.reponse;

import com.tuoyou.tavern.protocol.authcenter.model.LoginVO;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.Data;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
@Data
public class LoginResponse extends TavernResponse {
    private LoginVO data;

}
