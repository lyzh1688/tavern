package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.bank.service.BankStatementDtlCcblRecordService;
import com.tuoyou.tavern.alleria.bank.service.BankStatementRecordService;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDTO;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlDTO;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import com.tuoyou.tavern.protocol.alleria.model.Dict;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementResponse;
import com.tuoyou.tavern.protocol.alleria.response.DictResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/11 <br>
 */
@RestController
@RequestMapping("/alleria/bank")
public class BankStatementEndpoint {

    @Autowired
    private BankStatementRecordService bankStatementRecordService;
    @Autowired
    private BankStatementDtlCcblRecordService bankStatementDtlCcblRecordService;
    @Autowired
    private FileUploadRecordService fileUploadRecordService;
    @Value("${invoice.zzs.dir:/mnt/file/bank/}")
    private String bankStatementDir;

    /*
     * 查询银行流水
     *
     * */
    @GetMapping("/page")
    BankStatementResponse queryBankStatementRecord(Page page, BankStatementDTO bankStatementDTO) {
        return new BankStatementResponse(this.bankStatementRecordService.getBankStatementRecord(page, bankStatementDTO));
    }

    /*
     * 查询银行流水明细
     *
     * */
    @GetMapping("/dtl")
    BankStatementDtlResponse queryBankStatementDtlRecord(Page page, BankStatementDtlDTO bankStatementRecord) {
        return new BankStatementDtlResponse(this.bankStatementDtlCcblRecordService.getBankStatementDtlRecord(page, bankStatementRecord));
    }

    /*
     * 查询银行流水账户字典
     *
     * */
    @GetMapping("/recvAccntId")
    DictResponse queryRecvAccntIdDict(@RequestParam(name = "batchId", required = true) String batchId,
                                      @RequestParam(name = "recvAccntId") String recvAccntId) {
        return new DictResponse(this.bankStatementDtlCcblRecordService.getRecvAccntIdDict(batchId, recvAccntId)
                .stream()
                .map(dtl -> {
                    Dict dict = new Dict();
                    dict.setId(dtl.getFileId());
                    dict.setName(dtl.getRecvAccntName());
                    return dict;
                }).collect(Collectors.toList()));
    }

    /*
     * 上传银行流水文件
     *
     * **/
    @PostMapping("/upload")
    public TavernResponse uploadBankStatementFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("batchId") String batchId) throws Exception {
        String date = DateUtils.formatDate(LocalDate.now(), DateUtils.SIMPLE_8_FORMATTER);
        this.fileUploadRecordService.uploadFile(multipartFile,
                StringUtils.join(bankStatementDir, "/", date, "/"),
                "1", batchId,
                this.bankStatementRecordService::parseBankStatement);
        return new TavernResponse();
    }
}
