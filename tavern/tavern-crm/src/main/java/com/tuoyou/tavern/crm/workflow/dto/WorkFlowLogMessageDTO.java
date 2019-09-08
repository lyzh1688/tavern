package com.tuoyou.tavern.crm.workflow.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/9/5 <br>
 */
@Data
public class WorkFlowLogMessageDTO {

    private String operator;

    private String operatorName;

    private String message;

    private String eventId;

    private List<MultipartFile> files = Lists.newArrayList();

    private BigDecimal refundFee;

}
