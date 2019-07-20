package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.alleria.invoice.service.TaxScanResultService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResult;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResultVO;
import com.tuoyou.tavern.protocol.alleria.response.TaxScanResultPageResponse;
import com.tuoyou.tavern.protocol.alleria.response.TaxScanResultResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@RestController
@RequestMapping("/invoice/zzs")
public class ZZSInvoiceEndpoint {
    @Autowired
    private TaxScanResultService taxScanResultService;
    @Autowired
    private StdInvoiceDtlRecordService stdInvoiceDtlRecordService;
    @Autowired
    private StdInvoiceRecordService stdInvoiceRecordService;
    @Autowired
    private FileUploadRecordService fileUploadRecordService;
    @Value("${invoice.zzs.dir:/mnt/file/zzs/}")
    private String zzsDir;

    /*
     * 验证结果查询
     * */
    //RESOURCE-PATH:/invoice/zzs/{batchId}?page={page}&size={size}&verify={verify}&emend={emend}
    @GetMapping(value = "/scan")
    public TaxScanResultPageResponse queryInvoiceScanResult(Page page, TaxScanResultDTO taxScanResultDTO) {
        return new TaxScanResultPageResponse(taxScanResultService.getTaxScanResult(page, taxScanResultDTO));
    }

    /*
     * 更新增值税5字段信息
     * */
    @PutMapping(value = "/edit")
    public TavernResponse updateZzsInvoiceKeyField(@Valid @RequestBody ZZSInvoiceKeyField zzsInvoiceKeyField) {
        this.stdInvoiceDtlRecordService.updateZzsInvoiceDtl(zzsInvoiceKeyField);
        return new TavernResponse();
    }

    /*
     * 上传增值税zip文件
     *
     * **/
    @PostMapping("/upload")
    public TavernResponse uploadZzsInvoiceFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("batchId") String batchId) throws Exception {
        String date = DateUtils.formatDate(LocalDate.now(), DateUtils.SIMPLE_8_FORMATTER);
        this.fileUploadRecordService.uploadFile(multipartFile,
                StringUtils.join(zzsDir, "/", date, "/"),
                "3", batchId,
                this.stdInvoiceRecordService::parseZzsInvoice);
        return new TavernResponse();
    }

    /*
     * 单条验证结果查询
     * */
    @GetMapping(value = "/scan/{fileId}")
    public TaxScanResultResponse queryInvoiceScanResult(@PathVariable("fileId") String fileId) {
        TaxScanResultVO taxScanResultVO = taxScanResultService.getTaxScanResultByFileId(fileId);
        return new TaxScanResultResponse(taxScanResultVO);
    }

}
