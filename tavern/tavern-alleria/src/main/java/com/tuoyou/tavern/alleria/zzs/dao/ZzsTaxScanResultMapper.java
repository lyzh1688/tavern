package com.tuoyou.tavern.alleria.zzs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanResult;
import org.apache.ibatis.annotations.Param;

public interface ZzsTaxScanResultMapper extends BaseMapper<ZzsTaxScanResult> {
    IPage<ZzsTaxScanResult> selectTaxScanResult(Page page, @Param("query") TaxScanResultDTO taxScanResultDTO);
}