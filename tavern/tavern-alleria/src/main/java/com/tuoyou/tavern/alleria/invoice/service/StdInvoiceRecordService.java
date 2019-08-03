package com.tuoyou.tavern.alleria.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecordVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface StdInvoiceRecordService extends IService<StdInvoiceRecord> {

    void updateStatus(String batchId, String valid);

    void parseStdInvoice(FileTransfer fileTransfer);

    IPage<StdInvoiceRecordVO> getStdInvoiceRecord(Page page, StdInvoiceRecordDTO stdInvoiceRecordDTO);

}
