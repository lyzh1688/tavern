package com.tuoyou.tavern.alleria.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.alleria.entity.TaxScanResult;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface TaxScanResultService extends IService<TaxScanResult> {
   IPage<TaxScanResult> getTaxScanResult(Page page, TaxScanResultDTO taxScanResultDTO);
}
