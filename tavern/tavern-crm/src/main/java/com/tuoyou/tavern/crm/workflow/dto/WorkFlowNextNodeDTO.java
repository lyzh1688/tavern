package com.tuoyou.tavern.crm.workflow.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/06 <br>
 */
@Data
public class WorkFlowNextNodeDTO {
    private String eventId;
    private String curNodeId;
    private String curOperator;
    private String curOperatorName;
    private String message;
    private List<MultipartFile> files = Lists.newArrayList();
    private BigDecimal refundFee;

}
