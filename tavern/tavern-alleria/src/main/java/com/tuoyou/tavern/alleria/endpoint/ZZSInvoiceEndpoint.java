package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.TaxScanResultService;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.response.TaxScanResultResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/alleria/invoice/zzs")
public class ZZSInvoiceEndpoint {

    private final TaxScanResultService taxScanResultService;
    private final StdInvoiceDtlRecordService stdInvoiceDtlRecordService;

    /*
     * 验证结果查询
     * */
    //RESOURCE-PATH:/invoice/zzs/{batchId}?page={page}&size={size}&verify={verify}&emend={emend}
    @GetMapping(value = "/scan")
    public TaxScanResultResponse queryInvoiceScanResult(Page page, TaxScanResultDTO taxScanResultDTO) {
        return new TaxScanResultResponse(taxScanResultService.getTaxScanResult(page, taxScanResultDTO));
    }

    /*
     * 更新增值税5字段信息
     * */
    //RESOURCE-PATH:/invoice/zzs/{fileId}
    @PutMapping(value = "/edit")
    public TavernResponse updateZzsInvoiceKeyField(@Valid @RequestBody ZZSInvoiceKeyField zzsInvoiceKeyField) {
        this.stdInvoiceDtlRecordService.updateZzsInvoiceDtl(zzsInvoiceKeyField);
        return new TavernResponse();
    }
}
