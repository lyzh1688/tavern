package com.tuoyou.tavern.alleria.invoice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.invoice.dao.StdInvoiceRecordMapper;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceRecord;
import com.tuoyou.tavern.alleria.invoice.service.StdInvoiceRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class StdInvoiceRecordServiceImpl extends ServiceImpl<StdInvoiceRecordMapper, StdInvoiceRecord> implements StdInvoiceRecordService {


    @Override
    public void updateStatus(String fileId, String valid) {
        this.baseMapper.updateFileStatus(fileId, valid);
    }
}
