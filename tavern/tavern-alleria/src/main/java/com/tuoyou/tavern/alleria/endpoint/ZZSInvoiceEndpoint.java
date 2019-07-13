package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import com.tuoyou.tavern.alleria.invoice.service.TaxScanResultService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.response.TaxScanResultResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
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
@RequestMapping("/alleria/invoice/zzs")
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

    @PostMapping("/upload")
    public TavernResponse uploadZzsInvoiceFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String date = DateUtils.formatDate(LocalDate.now(), DateUtils.SIMPLE_8_FORMATTER);
        this.fileUploadRecordService.uploadFile(multipartFile, StringUtils.join(zzsDir, "/", date, "/"),"3", this.stdInvoiceRecordService::parseZzsInvoiceFile);
        return new TavernResponse();

    }

}
