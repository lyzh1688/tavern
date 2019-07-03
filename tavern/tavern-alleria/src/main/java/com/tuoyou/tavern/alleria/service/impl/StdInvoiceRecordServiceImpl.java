package com.tuoyou.tavern.alleria.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.dao.StdInvoiceRecordMapper;
import com.tuoyou.tavern.alleria.dto.ZZSInvoiceKeyField;
import com.tuoyou.tavern.alleria.entity.StdInvoiceRecord;
import com.tuoyou.tavern.alleria.service.StdInvoiceDtlRecordService;
import com.tuoyou.tavern.alleria.service.StdInvoiceRecordService;
import com.tuoyou.tavern.alleria.service.TaxScanResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class StdInvoiceRecordServiceImpl extends ServiceImpl<StdInvoiceRecordMapper, StdInvoiceRecord> implements StdInvoiceRecordService {


}
