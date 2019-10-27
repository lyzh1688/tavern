package com.tuoyou.tavern.protocol.crm.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.model.CrmThirdPartyInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CrmCustomThirdPartyPageResponse extends TavernResponse {
    private IPage<CrmThirdPartyInfo> data;
}
