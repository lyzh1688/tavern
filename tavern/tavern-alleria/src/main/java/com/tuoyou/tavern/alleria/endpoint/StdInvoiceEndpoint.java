package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.response.StdInvoiceRecordDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.StdInvoiceRecordResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/11 <br>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/alleria/invoice")
public class StdInvoiceEndpoint {

    private final StdInvoiceDtlRecordService stdInvoiceDtlRecordService;
    private final StdInvoiceRecordService stdInvoiceRecordService;

    /*
     * 查询发票记录
     *
     * */
    @GetMapping(value = "/record/page")
    public StdInvoiceRecordResponse queryStdInvoiceRecord(Page page, StdInvoiceRecord stdInvoiceRecord) {
        return new StdInvoiceRecordResponse(this.stdInvoiceRecordService.page(page, Wrappers.query(stdInvoiceRecord)));
    }

    /*
     * 查询发票记录详情
     *
     * */
    @GetMapping(value = "/dtl/page")
    public StdInvoiceRecordDtlResponse queryStdInvoiceRecordDtl(Page page, StdInvoiceDtlRecord stdInvoiceDtlRecord) {
        return new StdInvoiceRecordDtlResponse(this.stdInvoiceDtlRecordService.page(page, Wrappers.query(stdInvoiceDtlRecord)));
    }

    /*
     * 改变发票状态
     *
     * */
    @PutMapping("/valid/{fileId}/{valid}")
    public TavernResponse updateValid(@PathVariable("fileId") String fileId,
                                      @PathVariable("valid") String valid) {
        this.stdInvoiceRecordService.updateStatus(fileId, valid);
        return new TavernResponse();
    }

}
