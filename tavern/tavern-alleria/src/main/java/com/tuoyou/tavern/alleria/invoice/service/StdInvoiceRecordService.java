package com.tuoyou.tavern.alleria.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecordVO;
import com.tuoyou.tavern.protocol.alleria.model.TaxScanResultVO;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface StdInvoiceRecordService extends IService<StdInvoiceRecord> {

    void updateStatus(String batchId, String valid);

    void parseZzsInvoice(FileTransfer fileTransfer);

    void parseStdInvoice(FileTransfer fileTransfer) ;

    IPage<StdInvoiceRecordVO> getStdInvoiceRecord(Page page, StdInvoiceRecordDTO stdInvoiceRecordDTO);

}
