package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.invoice.service.ExtraInvoiceRecordService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.ExtraInvoiceDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ExtraInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.response.ExtraInvoiceRecordResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/12 <br>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/invoice/extra")
public class ExtraInvoiceRecordEndpoint {
    private final ExtraInvoiceRecordService extraInvoiceRecordService;

    /*
     * 查询其它补票记录
     *
     * */
    @GetMapping(value = "/page")
    public ExtraInvoiceRecordResponse queryExtraInvoiceRecord(Page page, ExtraInvoiceDTO extraInvoiceRecord) {
        return new ExtraInvoiceRecordResponse(this.extraInvoiceRecordService.getByPage(page, extraInvoiceRecord));
    }

    /*
     * 新增其它补票记录
     *
     **/
    @PostMapping("/save")
    public TavernResponse saveExtraInvoiceRecord(@Valid @RequestBody ExtraInvoiceRecordDTO extraInvoiceRecordDTO) {
        ExtraInvoiceRecord extraInvoiceRecord = new ExtraInvoiceRecord();
        BeanUtils.copyProperties(extraInvoiceRecordDTO, extraInvoiceRecord);
        extraInvoiceRecord.setAccountPeriod(DateUtils.parseDate(extraInvoiceRecordDTO.getAccountPeriod(), DateUtils.SIMPLE_8_FORMATTER));
        extraInvoiceRecord.setTotalAmount(StringUtils.isEmpty(extraInvoiceRecordDTO.getTotalAmount()) ? null : new BigDecimal(extraInvoiceRecordDTO.getTotalAmount()));
        extraInvoiceRecord.setItemCount(StringUtils.isEmpty(extraInvoiceRecordDTO.getItemCount()) ? null : Long.parseLong(extraInvoiceRecordDTO.getItemCount()));
        extraInvoiceRecord.setIsValid("1");
        this.extraInvoiceRecordService.saveOrUpdate(extraInvoiceRecord);
        return new TavernResponse();
    }

    /*
     * 修改其它补票记录
     *
     **/
    @PutMapping("/valid/{recordId}/{valid}")
    public TavernResponse updateExtraInvoiceRecord(@PathVariable("recordId") String recordId,
                                                   @PathVariable("valid") String valid) {
        ExtraInvoiceRecord extraInvoiceRecord = new ExtraInvoiceRecord();
        extraInvoiceRecord.setIsValid(valid);
        extraInvoiceRecord.setRecordId(recordId);
        this.extraInvoiceRecordService.updateById(extraInvoiceRecord);
        return new TavernResponse();
    }

}
