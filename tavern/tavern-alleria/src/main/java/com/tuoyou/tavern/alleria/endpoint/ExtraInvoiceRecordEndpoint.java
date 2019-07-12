package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.invoice.service.ExtraInvoiceRecordService;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.response.ExtraInvoiceRecordResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/12 <br>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/alleria/exra/invoice")
public class ExtraInvoiceRecordEndpoint {
    private final ExtraInvoiceRecordService extraInvoiceRecordService;

    /*
     * 查询其它补票记录
     *
     * */
    @GetMapping(value = "/page")
    public ExtraInvoiceRecordResponse queryExtraInvoiceRecord(Page page, ExtraInvoiceRecord extraInvoiceRecord) {
        return new ExtraInvoiceRecordResponse(this.extraInvoiceRecordService.page(page, Wrappers.query(extraInvoiceRecord)));
    }

    @PostMapping("save")
    public TavernResponse saveExtraInvoiceRecord(@Valid @RequestBody ExtraInvoiceRecord extraInvoiceRecord) {
        this.extraInvoiceRecordService.save(extraInvoiceRecord);
        return new TavernResponse();
    }
}
