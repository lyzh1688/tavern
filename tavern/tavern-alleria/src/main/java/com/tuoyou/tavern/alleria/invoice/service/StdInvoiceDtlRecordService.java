package com.tuoyou.tavern.alleria.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecordVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface StdInvoiceDtlRecordService extends IService<StdInvoiceDtlRecord> {

     void updateZzsInvoiceDtl(ZZSInvoiceKeyField zzsInvoiceKeyField);
     IPage<StdInvoiceDtlRecordVO> getStdInvoiceDtlRecord(Page page, StdInvoiceRecordDTO stdInvoiceRecordDTO);
}
