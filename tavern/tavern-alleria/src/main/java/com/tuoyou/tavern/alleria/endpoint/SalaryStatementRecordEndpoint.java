package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.alleria.salary.service.SalaryStatementDtlRecordService;
import com.tuoyou.tavern.alleria.salary.service.SalaryStatementRecordService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.SalaryStatementRecordDTO;
import com.tuoyou.tavern.protocol.alleria.response.SalaryStatementRecordDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.SalaryStatementRecordResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/13 <br>
 */
@RestController
@RequestMapping("/salary")
public class SalaryStatementRecordEndpoint {
    @Autowired
    private SalaryStatementDtlRecordService salaryStatementDtlRecordService;
    @Autowired
    private SalaryStatementRecordService salaryStatementRecordService;
    @Autowired
    private FileUploadRecordService fileUploadRecordService;
    @Value("${invoice.salary.dir:/mnt/file/salary/}")
    private String salaryDir;

    /*
     * 查询工资记录
     *
     * */
    @GetMapping(value = "/page")
    public SalaryStatementRecordResponse querySalaryStatementRecord(Page page, SalaryStatementRecordDTO salaryStatementRecordDTO) {
        return new SalaryStatementRecordResponse(this.salaryStatementRecordService.getSalaryStatementRecord(page, salaryStatementRecordDTO));
    }

    /*
     * 查询工资单详情
     *
     * */
    @GetMapping(value = "/dtl/page")
    public SalaryStatementRecordDtlResponse querySalaryStatementRecordDtl(Page page, SalaryStatementRecordDTO salaryStatementRecordDTO) {
        return new SalaryStatementRecordDtlResponse(this.salaryStatementDtlRecordService.getSalaryStatementDtlRecord(page, salaryStatementRecordDTO));
    }

    /*
     * 改变工资单状态
     *
     * */
    @PutMapping("/valid/{batchId}/{valid}")
    public TavernResponse updateValid(@PathVariable("batchId") String batchId,
                                      @PathVariable("valid") String valid) {
        this.salaryStatementRecordService.updateStatus(batchId, valid);
        return new TavernResponse();
    }

    /*
     * 上传工资单zip文件
     *
     * **/
    @PostMapping("/upload")
    public TavernResponse uploadSalaryStatementFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("batchId") String batchId) throws Exception {
        String date = DateUtils.formatDate(LocalDate.now(), DateUtils.SIMPLE_8_FORMATTER);
        this.fileUploadRecordService.uploadFile(multipartFile,
                StringUtils.join(salaryDir, "/", date, "/"),
                "4", batchId,
                this.salaryStatementRecordService::parseSalaryStatement);
        return new TavernResponse();
    }
}
