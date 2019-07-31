package com.tuoyou.tavern.alleria.invoice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.dto.ExtraInvoiceDTO;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecord;
import com.tuoyou.tavern.protocol.alleria.model.ExtraInvoiceRecordVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/12 <br>
 */
public interface ExtraInvoiceRecordService extends IService<ExtraInvoiceRecord> {

    IPage<ExtraInvoiceRecordVO> getByPage(Page page, ExtraInvoiceDTO extraInvoiceDTO);
}
