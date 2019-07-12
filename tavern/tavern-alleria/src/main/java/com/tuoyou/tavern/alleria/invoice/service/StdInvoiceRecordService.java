package com.tuoyou.tavern.alleria.invoice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface StdInvoiceRecordService extends IService<StdInvoiceRecord> {

    void updateStatus(String fileId,String valid);

}
