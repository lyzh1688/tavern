package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderBusinessRelService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderInfoService;
import com.tuoyou.tavern.crm.crm.dao.CrmCustomOrderInfoMapper;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyOrderQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfoVO;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomOrderInfoServiceImpl extends ServiceImpl<CrmCustomOrderInfoMapper, CrmCustomOrderInfo> implements CrmCustomOrderInfoService {

    private final CrmCustomOrderBusinessRelService crmCustomOrderBusinessRelService;

    @Override
    public void createCrmCustomOrderInfo(CrmCustomOrderInfo crmCustomOrderInfo) throws Exception {
        if (Objects.isNull(crmCustomOrderInfo.getId())) {
            CrmCustomOrderInfo orderInfo = this.getOne(Wrappers.<CrmCustomOrderInfo>query().lambda()
                    .eq(CrmCustomOrderInfo::getOrderId, crmCustomOrderInfo.getOrderId()));
            if (Objects.nonNull(orderInfo)) {
                throw new Exception("已有相同的订单号，添加错误！");
            }
        } else {
            CrmCustomOrderInfo orderInfo = this.getOne(Wrappers.<CrmCustomOrderInfo>query().lambda()
                    .eq(CrmCustomOrderInfo::getOrderId, crmCustomOrderInfo.getOrderId()));
            if (Objects.nonNull(orderInfo) && !orderInfo.getId().equals(crmCustomOrderInfo.getId())) {
                throw new Exception("已有相同的订单号，添加错误！");
            }
        }
        if (Objects.isNull(crmCustomOrderInfo.getId())) {
            this.baseMapper.insertOne(crmCustomOrderInfo);
        } else {
            this.baseMapper.updateOne(crmCustomOrderInfo);
        }

    }

    @Override
    public void deleteCrmCustomOrderInfo(String orderId) throws Exception {
        int count = this.crmCustomOrderBusinessRelService.count(Wrappers.<CrmOrderBusinessRel>query().lambda()
                .eq(CrmOrderBusinessRel::getOrderId, orderId));
        if(count != 0){
            throw new Exception("该订单尚有关联业务，不可删除！");
        }
        this.removeById(orderId);
    }

    @Override
    public IPage<CrmCustomOrderInfoVO> getCrmOrderPage(Page page, CustomCompanyOrderQueryDTO customCompanyOrderQueryDTO) {
        return this.baseMapper.selectCrmOrderPage(page, customCompanyOrderQueryDTO);
    }
}
