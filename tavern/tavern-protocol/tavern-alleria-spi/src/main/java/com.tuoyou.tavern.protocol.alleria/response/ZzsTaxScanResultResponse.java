package com.tuoyou.tavern.protocol.alleria.response;

import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanResultVO;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/11 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ZzsTaxScanResultResponse extends TavernResponse {
    private ZzsTaxScanResultVO data;
}
