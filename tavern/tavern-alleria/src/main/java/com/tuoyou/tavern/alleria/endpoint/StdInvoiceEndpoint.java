package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.response.StdInvoiceRecordDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.StdInvoiceRecordResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Collections;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/11 <br>
 */
@RestController
@RequestMapping("/invoice")
public class StdInvoiceEndpoint {
    @Autowired
    private StdInvoiceDtlRecordService stdInvoiceDtlRecordService;
    @Autowired
    private StdInvoiceRecordService stdInvoiceRecordService;
    @Autowired
    private FileUploadRecordService fileUploadRecordService;
    @Value("${invoice.std.dir:/mnt/file/std/}")
    private String zzsDir;

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

    /*
     * 查询发票记录详情
     *
     * */
    @GetMapping(value = "/dtl/{fileId}")
    public StdInvoiceRecordDtlResponse queryStdInvoiceRecordDtl(@PathVariable String fileId) {
        StdInvoiceDtlRecord stdInvoiceDtlRecord = this.stdInvoiceDtlRecordService.getOne(Wrappers.<StdInvoiceDtlRecord>query().lambda().eq(StdInvoiceDtlRecord::getFileId, fileId));
        IPage<StdInvoiceDtlRecord> invoiceDtlRecordIPage = new Page<>();
        invoiceDtlRecordIPage.setRecords(Collections.singletonList(stdInvoiceDtlRecord));
        return new StdInvoiceRecordDtlResponse(invoiceDtlRecordIPage);
    }

    /*
     * 上传发票zip文件
     *
     * **/
    @PostMapping("/std/upload")
    public TavernResponse uploadStdInvoiceFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("batchId") String batchId) throws Exception {
        String date = DateUtils.formatDate(LocalDate.now(), DateUtils.SIMPLE_8_FORMATTER);
        this.fileUploadRecordService.uploadFile(multipartFile,
                StringUtils.join(zzsDir, "/", date, "/"),
                "2", batchId,
                this.stdInvoiceRecordService::parseStdInvoice);
        return new TavernResponse();
    }
}
