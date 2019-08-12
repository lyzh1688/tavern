package com.tuoyou.tavern.protocol.authcenter.reponse;

import com.tuoyou.tavern.protocol.authcenter.model.AuthMenu;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/8/12 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AuthMenuResponse extends TavernResponse {

    private List<AuthMenu> data;


}
