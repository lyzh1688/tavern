package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tuoyou.tavern.alleria.common.service.InvoiceTypeDictService;
import com.tuoyou.tavern.protocol.alleria.model.Dict;
import com.tuoyou.tavern.protocol.alleria.model.InvoiceTypeDict;
import com.tuoyou.tavern.protocol.alleria.response.DictResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /*
     *
     * 新增票据字典
     *
     * **/
    @PostMapping("/save")
    public TavernResponse saveInvoiceType(@Valid @RequestBody InvoiceTypeDict invoiceTypeDict) {
        this.invoiceTypeDictService.save(invoiceTypeDict);
        return new TavernResponse();
    }

    /*
     *
     * 修改票据字典
     *
     * **/
    @PutMapping("/update")
    public TavernResponse updateInvoiceType(@Valid @RequestBody InvoiceTypeDict invoiceTypeDict) {
        this.invoiceTypeDictService.updateById(invoiceTypeDict);
        return new TavernResponse();
    }

    /*
     *
     * 删除票据字典
     *
     * **/
    @DeleteMapping
    public TavernResponse deleteInvoiceType(@Valid @RequestBody InvoiceTypeDict invoiceTypeDict) {
        this.invoiceTypeDictService.removeById(invoiceTypeDict);
        return new TavernResponse();
    }


}
