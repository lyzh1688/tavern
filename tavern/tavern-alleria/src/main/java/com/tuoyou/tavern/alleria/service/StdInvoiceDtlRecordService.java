package com.tuoyou.tavern.alleria.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.alleria.entity.StdInvoiceDtlRecord;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface StdInvoiceDtlRecordService extends IService<StdInvoiceDtlRecord> {

     Boolean updateZzsInvoiceDtl(ZZSInvoiceKeyField zzsInvoiceKeyField);
}
