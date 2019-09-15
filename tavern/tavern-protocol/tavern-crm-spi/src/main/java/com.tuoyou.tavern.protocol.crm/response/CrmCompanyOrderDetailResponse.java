package com.tuoyou.tavern.protocol.crm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyOrderDetailVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/15 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CrmCompanyOrderDetailResponse extends TavernResponse {
    private CrmCompanyOrderDetailVO data;

}
