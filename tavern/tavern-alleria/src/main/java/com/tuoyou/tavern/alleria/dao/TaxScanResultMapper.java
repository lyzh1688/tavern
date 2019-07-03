package com.tuoyou.tavern.alleria.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.alleria.entity.TaxScanResult;
import org.apache.ibatis.annotations.Param;

public interface TaxScanResultMapper extends BaseMapper<TaxScanResult> {

    IPage<TaxScanResult> selectTaxScanResult(Page page, @Param("query") TaxScanResultDTO taxScanResultDTO);
}