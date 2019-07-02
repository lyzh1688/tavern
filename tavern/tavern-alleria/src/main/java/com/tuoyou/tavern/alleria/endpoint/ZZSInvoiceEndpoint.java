package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.alleria.model.ZZSInvoiceScanResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@RestController
@RequestMapping("/alleria/invoice/zzs")
public class ZZSInvoiceEndpoint {

    /*
     * 验证结果查询
     * */
    //RESOURCE-PATH:/invoice/zzs/{batchId}?page={page}&size={size}&verify={verify}&emend={emend}
    @GetMapping(value = "/scan")
    public ZZSInvoiceScanResult queryInvoiceScanResult(Page page, TaxScanResultDTO taxScanResultDTO) {
        return new ZZSInvoiceScanResult();
    }

    /*
     * 更新增值税5字段信息
     * */
    //RESOURCE-PATH:/invoice/zzs/{fileId}
    @PutMapping(value = "/edit")
    public boolean updateZzsInvoiceKeyField(@Valid @RequestBody ZZSInvoiceKeyField zzsInvoiceKeyField) {
        return true;
    }
}
