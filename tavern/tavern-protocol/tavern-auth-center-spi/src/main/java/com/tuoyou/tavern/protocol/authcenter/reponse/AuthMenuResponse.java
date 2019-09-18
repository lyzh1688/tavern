package com.tuoyou.tavern.protocol.authcenter.reponse;

import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.authcenter.model.AuthMenuVO;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AuthMenuResponse extends TavernResponse {
    public AuthMenuResponse() {
        super();
    }

    private List<AuthMenuVO> data;


}
