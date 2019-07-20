package com.tuoyou.tavern.protocol.alleria.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResult;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResultVO;
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
public class TaxScanResultPageResponse extends TavernResponse {
    private IPage<TaxScanResultVO> data;
}
