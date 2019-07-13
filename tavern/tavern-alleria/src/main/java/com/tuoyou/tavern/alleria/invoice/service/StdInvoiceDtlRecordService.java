package com.tuoyou.tavern.alleria.invoice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecord;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface StdInvoiceDtlRecordService extends IService<StdInvoiceDtlRecord> {

     void updateZzsInvoiceDtl(ZZSInvoiceKeyField zzsInvoiceKeyField);

}
