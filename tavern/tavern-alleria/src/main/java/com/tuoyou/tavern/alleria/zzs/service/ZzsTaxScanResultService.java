package com.tuoyou.tavern.alleria.zzs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.protocol.alleria.dto.TaxScanResultDTO;
import com.tuoyou.tavern.protocol.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanResult;
import com.tuoyou.tavern.protocol.alleria.model.ZzsTaxScanResultVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/03 <br>
 */
public interface ZzsTaxScanResultService extends IService<ZzsTaxScanResult> {
    void parseZzsInvoice(FileTransfer fileTransfer);

    IPage<ZzsTaxScanResultVO> getZzsTaxScanResult(Page page, TaxScanResultDTO taxScanResultDTO);

    ZzsTaxScanResultVO getTaxScanResultByFileId(String fileId);

    void updateZzsInvoiceDtl(ZZSInvoiceKeyField zzsInvoiceKeyField) throws Exception;

}
