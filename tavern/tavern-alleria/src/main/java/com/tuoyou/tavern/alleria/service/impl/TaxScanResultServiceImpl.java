package com.tuoyou.tavern.alleria.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.dao.TaxScanResultMapper;
import com.tuoyou.tavern.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.alleria.entity.TaxScanResult;
import com.tuoyou.tavern.alleria.service.TaxScanResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class TaxScanResultServiceImpl extends ServiceImpl<TaxScanResultMapper, TaxScanResult> implements TaxScanResultService {
    @Override
    public IPage<TaxScanResult> getTaxScanResult(Page page, TaxScanResultDTO taxScanResultDTO) {
        return this.baseMapper.selectTaxScanResult(page, taxScanResultDTO);
    }
}