package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tuoyou.tavern.alleria.common.service.InvoiceTypeDictService;
import com.tuoyou.tavern.protocol.alleria.model.Dict;
import com.tuoyou.tavern.protocol.alleria.model.InvoiceTypeDict;
import com.tuoyou.tavern.protocol.alleria.response.DictResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/12 <br>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/alleria/invoice/dict")
public class InvoiceTypeDictEndpoint {

    private InvoiceTypeDictService invoiceTypeDictService;

    /*
     * 查询发票类型字典
     *
     * */
    @GetMapping("/type")
    DictResponse queryInvoiceDict(@RequestParam(name = "input") String input) {
        return new DictResponse(this.invoiceTypeDictService
                .list(Wrappers.<InvoiceTypeDict>query().lambda().like(InvoiceTypeDict::getInvoiceTypeName, input))
                .stream()
                .map(dict -> {
                    Dict tmpDict = new Dict();
                    tmpDict.setId(dict.getInvoiceType());
                    tmpDict.setName(dict.getInvoiceTypeName());
                    return tmpDict;
                }).collect(Collectors.toList()));
    }

}
