package com.tuoyou.tavern.alleria.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.tuoyou.tavern.alleria.entity.TaxScanResult;
import java.util.List;

public interface TaxScanResultMapper  extends BaseMapper<TaxScanResult> {
    int deleteByPrimaryKey(String fileId);

    int insert(TaxScanResult record);

    TaxScanResult selectByPrimaryKey(String fileId);

    List<TaxScanResult> selectAll();

    int updateByPrimaryKey(TaxScanResult record);
}