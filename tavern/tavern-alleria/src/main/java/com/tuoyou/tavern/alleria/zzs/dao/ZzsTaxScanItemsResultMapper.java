package com.tuoyou.tavern.alleria.zzs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanItemsResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZzsTaxScanItemsResultMapper extends BaseMapper<ZzsTaxScanItemsResult> {
    int deleteByPrimaryKey(@Param("invoiceId") String invoiceId, @Param("invoiceCode") String invoiceCode);

    int insert(ZzsTaxScanItemsResult record);

    ZzsTaxScanItemsResult selectByPrimaryKey(@Param("invoiceId") String invoiceId, @Param("invoiceCode") String invoiceCode);

    List<ZzsTaxScanItemsResult> selectAll();

    int updateByPrimaryKey(ZzsTaxScanItemsResult record);
}