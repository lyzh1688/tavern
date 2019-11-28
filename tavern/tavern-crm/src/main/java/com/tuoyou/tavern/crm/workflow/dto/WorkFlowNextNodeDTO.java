package com.tuoyou.tavern.crm.workflow.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
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
    private String preNodeId;
    private String curNodeId;
    private String curOperator;
    private String curOperatorName;
    private String operator;
    private String operatorName;
    private String message;
    private List<MultipartFile> files = Lists.newArrayList();
    private BigDecimal refundFee;


    private String thirdPartyFlag;
    private String thirdPartyId;
    private String thirdPartyInfo;
    private String thirdPartyFee;

    public WorkFlowNextNodeDTO(String eventId, String preNodeId, String curNodeId, String curOperator, String curOperatorName, String operator, String operatorName, String message, List<MultipartFile> files, BigDecimal refundFee, String thirdPartyFlag, String thirdPartyId, String thirdPartyInfo, String thirdPartyFee) {
        this.eventId = eventId;
        this.preNodeId = preNodeId;
        this.curNodeId = curNodeId;
        this.curOperator = curOperator;
        this.curOperatorName = curOperatorName;
        this.operator = operator;
        this.operatorName = operatorName;
        this.message = message;
        this.files = files;
        this.refundFee = refundFee;
        this.thirdPartyFlag = thirdPartyFlag;
        this.thirdPartyId = thirdPartyId;
        this.thirdPartyInfo = thirdPartyInfo;
        this.thirdPartyFee = thirdPartyFee;
    }

    public WorkFlowNextNodeDTO(String eventId, String preNodeId, String curNodeId, String curOperator, String curOperatorName, String operator, String operatorName, String message, List<MultipartFile> files, BigDecimal refundFee) {
        this.eventId = eventId;
        this.preNodeId = preNodeId;
        this.curNodeId = curNodeId;
        this.curOperator = curOperator;
        this.curOperatorName = curOperatorName;
        this.operator = operator;
        this.operatorName = operatorName;
        this.message = message;
        this.files = files;
        this.refundFee = refundFee;
    }

    @Override
    public String toString() {
        return "WorkFlowNextNodeDTO{" +
                "eventId='" + eventId + '\'' +
                ", preNodeId='" + preNodeId + '\'' +
                ", curNodeId='" + curNodeId + '\'' +
                ", curOperator='" + curOperator + '\'' +
                ", curOperatorName='" + curOperatorName + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", message='" + message + '\'' +
                ", files=" + files +
                ", refundFee=" + refundFee +
                ", thirdPartyFlag='" + thirdPartyFlag + '\'' +
                ", thirdPartyId='" + thirdPartyId + '\'' +
                ", thirdPartyInfo='" + thirdPartyInfo + '\'' +
                ", thirdPartyFee='" + thirdPartyFee + '\'' +
                '}';
    }
}
