package com.tuoyou.tavern.protocol.authcenter.reponse;

import com.tuoyou.tavern.protocol.authcenter.model.AuthRoleInfo;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/11/17 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AuthRoleInfoResponse extends TavernResponse {
    private AuthRoleInfo data;
}
