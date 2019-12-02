package com.tuoyou.tavern.crm.workflow.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Data
public class WorkFlowLogMessageDTO {

    private String operator;

    private String operatorName;

    private String message;

    private String eventId;

    private List<MultipartFile> files = Lists.newArrayList();

    private BigDecimal refundFee;

    private String curNodeId;

    private String refundWay;

}
