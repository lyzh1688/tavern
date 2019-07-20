package com.tuoyou.tavern.alleria.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResult;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResultVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface TaxScanResultService extends IService<TaxScanResult> {
    IPage<TaxScanResultVO> getTaxScanResult(Page page, TaxScanResultDTO taxScanResultDTO);

    TaxScanResultVO getTaxScanResultByFileId(String fileId);
}
