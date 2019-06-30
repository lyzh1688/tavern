package com.tuoyou.tavern.alleria.endpoint;

import com.tuoyou.tavern.alleria.model.ZZSInvoiceKeyField;
import com.tuoyou.tavern.alleria.model.ZZSInvoiceScanResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@RestController
public class ZZSInvoiceEndpoint {

    /*
    * 验证结果查询
    * */
    //RESOURCE-PATH:/invoice/zzs/{batchId}?page={page}&size={size}&verify={verify}&emend={emend}
    @RequestMapping(value = "/invoice/zzs/{batchId}",method = RequestMethod.GET,params = {"page","size","verify","emend"})
    public ZZSInvoiceScanResult queryInvoiceScanResult(@PathVariable String batchId, Integer page, Integer size, String verify, String emend){
        return new ZZSInvoiceScanResult();
    }

    /*
    * 更新增值税5字段信息
    * */
    //RESOURCE-PATH:/invoice/zzs/{fileId}
    @RequestMapping(value = "/invoice/zzs/{fileId}",method = RequestMethod.PUT)
    public boolean updateZzsInvoiceKeyField(@PathVariable String fileId, @RequestBody ZZSInvoiceKeyField zzsInvoiceKeyField){
        return true;
    }
}
